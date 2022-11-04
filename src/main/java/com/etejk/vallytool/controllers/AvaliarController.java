package com.etejk.vallytool.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.dao.ResultadoDAO;
import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.PegarResultado;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.TrimestreDatabase;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TrimestreAtualRepository;
import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.services.ResultadoService;

@Controller
@RequestMapping("avaliar")
public class AvaliarController {
	
	@Autowired
	public ResultadoRepository rr;

	@Autowired
	private TurmaRepository tr;
	
	@Autowired
	private ProfessoRepository pr;
	
	@Autowired
	private DisciplinaRepository dr;
	
	@Autowired
	private CompetenciaRepository cr;
	
	@Autowired
	private ResultadoService rs;
	
	@Autowired
	private ResultadoRepository resr;

	@Autowired
	private RelacaoRepository rer;
	
	@Autowired
	private TrimestreAtualRepository tar;
	
	@GetMapping("turmas")
	public String turmas(Model model, Authentication auth) {
		Usuario usuario = pr.findByNome(auth.getName());
		model.addAttribute("competencias", cr.findAll());
		List<Turma> turmas = usuario.getTurmas();
		PegarResultado pegarResultado = new PegarResultado(resr, rer);
		if(turmas.isEmpty()) {
			turmas = null;
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("ano", LocalDate.now().getYear());
		model.addAttribute("pegarResultado" ,pegarResultado);
		model.addAttribute("turmas", turmas);
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		return "site/inicio";
	}
	
	@GetMapping("avaliar-turma")
    public String avaliar(Model model, Authentication auth,
            @RequestParam(value = "turma") String turma) {
        Usuario usuario = pr.findByNome(auth.getName());
        Turma turmaEnt = tr.findByCodigo(turma);
        
        List<Relacao> relacoes = rer.findByTurmaAndUsuario(turmaEnt, usuario);
        List<Disciplina> disciplinas = new ArrayList<>();
        for (Relacao relacao : relacoes) {
			disciplinas.add(relacao.getDisciplina());
		}
        
        model.addAttribute("turma", turmaEnt);
        model.addAttribute("disciplinas", disciplinas);
        model.addAttribute("trimestre", tar.getTrimestreAtual());
        model.addAttribute("competencias", cr.findAll());
        model.addAttribute("usuario", usuario);
        return "site/avaliar_turma";
    }

	@GetMapping("error")
	public ModelAndView avaliarErro(ModelMap model) {
		model.addAttribute("error", "Avaliação já foi realizada!");
		return new ModelAndView("redirect:/avaliar/turmas", model);
	}
	
	@GetMapping("trimestre-error")
	public ModelAndView trimestreErro(ModelMap model) {
		model.addAttribute("error", "Trimestre indisponível.");
		return new ModelAndView("redirect:/avaliar/turmas", model);
	}
	
	@GetMapping("trimestre-fechado-error")
	public ModelAndView trimestreFechadoErro(ModelMap model) {
		model.addAttribute("error", "Oops, o trimestre está fechado");
		return new ModelAndView("redirect:/avaliar/turmas", model);
	}
	
	@PostMapping("avaliar-turma")
	public ModelAndView avaliar(ModelMap model, @Valid ResultadoDAO resultado) {
		System.out.println(resultado);
		
		Trimestre trimestre = Trimestre.valueOf(resultado.getTrimestre());
		TrimestreDatabase trimestreDatabase = tar.findAll().get(0);
		Trimestre trimestreAtual = trimestreDatabase.getTrimestre();
		if(trimestre != trimestreAtual) {
			return new ModelAndView("redirect:/avaliar/trimestre-error");
		}
		
		if(!trimestreDatabase.isAberto()) {
			return new ModelAndView("redirect:/avaliar/trimestre-fechado-error");
		}
		
		Turma turma = tr.findByCodigo(resultado.getTurma());
		if(turma == null) {
			model.addAttribute("error", "Turma inexistente!");
			return new ModelAndView("redirect:/avaliar/turmas", model);
		}
		Disciplina disciplina = dr.findByNome(resultado.getDisciplina());
		if(disciplina == null) {
			model.addAttribute("error", "Disciplina inexistente!");
			return new ModelAndView("redirect:/avaliar/turmas", model);
		}
		Usuario usuario = pr.findByNome(resultado.getUsuario());
		if(usuario == null) {
			model.addAttribute("error", "Usuário inexistente!");
			return new ModelAndView("redirect:/avaliar/turmas", model);
		}
		Resultado resultadoOriginal = new Resultado(turma,
				disciplina,
				resultado.addConceitos(),
				usuario,
				Trimestre.valueOf(resultado.getTrimestre()));
		
		
		if(rs.verificar(resultadoOriginal.getData(),
				resultadoOriginal.getTrimestre(),
				resultadoOriginal)) 
		{
			rr.save(resultadoOriginal);
		} else {			
			return new ModelAndView("redirect:/avaliar/error");
		}
		
		model.addAttribute("sucess", "Resultado salvo!");
		return new ModelAndView("redirect:/avaliar/turmas", model);
	}
}

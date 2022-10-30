package com.etejk.vallytool.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.dao.ResultadoDAO;
import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.services.ResultadoService;
import com.etejk.vallytool.services.TurmaService;

@Controller
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
	private RelacaoRepository rer;
	
	@GetMapping("avaliar")
	public String avaliar(Model model, Authentication auth,
			@Param(value = "turma") String turma) {
		Usuario usuario = pr.findByNome(auth.getName());
		model.addAttribute("competencias", cr.findAll());
		model.addAttribute("turmas", usuario.getTurmas());
		model.addAttribute("disciplinas", usuario.getDisciplinas());
		System.out.println(turma);
		model.addAttribute("relacoes", rer.findByUsuario(usuario));
		model.addAttribute("usuario", usuario);
		return "site/avaliar_turma";
	}
	
	@GetMapping("avaliar/error")
	public ModelAndView avaliarErro(ModelMap model) {
		model.addAttribute("error", "Avaliação já foi realizada!");
		return new ModelAndView("redirect:/avaliar", model);
	}
	
	@PostMapping("/avaliar")
	public String avaliar(@Valid ResultadoDAO resultado, Model model) {
		System.out.println(resultado);
		
		
		
		Turma turma = tr.findByCodigo(resultado.getTurma());
		Disciplina disciplina = dr.findByNome(resultado.getDisciplina());
		Usuario usuario = pr.findByNome(resultado.getUsuario());
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
			return "redirect:/avaliar/error";
		}
		
		return "redirect:/avaliar";
	}
}

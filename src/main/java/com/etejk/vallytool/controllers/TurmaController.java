package com.etejk.vallytool.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.dialect.Teradata14Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.PegarResultado;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.TrimestreDatabase;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.DisciplinaRepository;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TrimestreAtualRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

@Controller
@RequestMapping("turmas")
public class TurmaController {
	
	@Autowired
	TurmaRepository tr;
	
	@Autowired
	ResultadoRepository rr;
	
	@Autowired
	DisciplinaRepository dr;
	
	@Autowired
	RelacaoRepository rer;
	
	@Autowired
	TrimestreAtualRepository tar;
	
	@Autowired
	CompetenciaRepository cr;
	
	@PostMapping("")
	public ModelAndView saveTurma(@Valid Turma turma, ModelMap model) {
		if(tr.findByCodigo(turma.getCodigo()) != null) {
			return new ModelAndView("redirect:/turmas/error");
		}
		
		turma.setAtivada(true);
		tr.save(turma);
		model.addAttribute("sucess", "Turma Salva!");
		return new ModelAndView("redirect:/turmas");
	}
	
	@GetMapping("/error")
	public ModelAndView turmaError(ModelMap model) {
		model.addAttribute("error", "Turma já Existe!");
		return new ModelAndView("redirect:/turmas", model);
	}
	
	@GetMapping("")
	public String turmas(Model model, @Param("search") String search) {
		if(search != null) {
		model.addAttribute("turmas", tr.search(search));
		}else {
			model.addAttribute("turmas", tr.findAll());
		}
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		return "site/turmas";
	}
	
	
	@GetMapping("editar-turma")
	public String editarTurma(Model model, @RequestParam(name ="turma") String turma) {
		Turma turmaEnt = tr.findByCodigo(turma);
		List<Relacao> relacoes = rer.findByTurma(turmaEnt);
		List<Disciplina> disciplinas = new ArrayList<>();
		for (Relacao relacao : relacoes) {
			Disciplina disciplina = relacao.getDisciplina();
			if(!disciplinas.contains(disciplina)) {
				disciplinas.add(disciplina);
			}
		}
		
		model.addAttribute("disciplinas", disciplinas);
		model.addAttribute("turma", turmaEnt);
		List<Resultado> resultados = rr.findByTurma(turmaEnt);
		model.addAttribute("resultados", resultados.isEmpty() ? null : resultados);
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		return "site/editar-turma";
	}
	@PostMapping("editar-turma")
	public ModelAndView editarTurma(ModelMap model,
			@RequestParam(name = "turma") String turma,
			@RequestParam(name = "disciplina") String disciplina) {
		
		Turma turmaEnt = tr.findByCodigo(turma);
		if(turma == null) {
			return new ModelAndView("redirect:/turmas/error");
		}
		Disciplina disciplinaEnt = dr.findByNome(disciplina);
		if(disciplinaEnt == null) {
			Disciplina disciplinaNova = new Disciplina(disciplina);
			dr.save(disciplinaNova);
			Relacao relacao = new Relacao(turmaEnt, disciplinaNova);
			rer.save(relacao);
			
			model.addAttribute("sucess", "Turma Editada!");
			return new ModelAndView("redirect:/turmas/editar-turma?turma=" + turma, model);
		}
		List<Relacao> relacoes = rer.findByTurma(turmaEnt);
		List<Disciplina> disciplinas = new ArrayList<>();
		for (Relacao relacao : relacoes) {
			Disciplina disciplinaFor = relacao.getDisciplina();
			if(!disciplinas.contains(disciplinaFor)) {
				disciplinas.add(disciplinaFor);
			}
		}
		if(!disciplinas.contains(disciplinaEnt)) {
			Relacao relacao = new Relacao(turmaEnt, disciplinaEnt);
			rer.save(relacao);
		}
		
		model.addAttribute("sucess", "Turma Editada!");
		return new ModelAndView("redirect:/turmas/editar-turma?turma=" + turma, model);
	}
	
	@PostMapping("alterar-turma")
	public ModelAndView alterarTurma(ModelMap model, @RequestParam(name="turma") String turma,
								@RequestParam(name="ativada") boolean ativada){
		Turma turmaEnt = tr.findByCodigo(turma);
		System.out.println(ativada);
		turmaEnt.setAtivada(ativada);
		
		tr.save(turmaEnt);
		model.addAttribute("sucess", "Turma Alterada!");
		return new ModelAndView("redirect:/turmas/editar-turma?turma=" + turma);
	}
	
	@PostMapping("clonar-turma")
	public ModelAndView clonarTurma(ModelMap model, @RequestParam(name="turma") String turma) {
		Turma turmaEnt = tr.findByCodigo(turma);
		String novoCodigo = String.valueOf(Integer.parseInt(turmaEnt.getCodigo()) + 1);
		
		List<Relacao> relacoes = rer.findByTurma(turmaEnt);
		List<Disciplina> disciplinas = new ArrayList<>();
		for (Relacao relacao : relacoes) {
			Disciplina disciplina = relacao.getDisciplina();
			if(!disciplinas.contains(disciplina)) {
				disciplinas.add(disciplina);
			}
		}
		
		Turma turmaClonada = new Turma(novoCodigo);
		tr.save(turmaClonada);
		
		for(Disciplina disciplina: disciplinas) {
			Relacao relacao = new Relacao(turmaClonada, disciplina);
			rer.save(relacao);
		}
		
		model.addAttribute("sucess", "Turma Clonada!");
		return new ModelAndView("redirect:/turmas/editar-turma?turma=" + novoCodigo, model);
	}
	
	@PostMapping("remover-turma")
	public ModelAndView removerTurma(ModelMap model, @RequestParam(name = "turma") String turma) {
		Turma turmaEnt = tr.findByCodigo(turma);
		tr.delete(turmaEnt);
		
		model.addAttribute("sucess", "Turma Removida!");
		return new ModelAndView("redirect:/turmas", model);
	}
	
	@PostMapping("remover-disciplina")
	public ModelAndView removerDisciplina(ModelMap model, @RequestParam(name = "turma") String turma,
									@RequestParam(name = "disciplina") String disciplina) {
		Turma turmaEnt = tr.findByCodigo(turma);
		Disciplina disciplinaEnt = dr.findByNome(disciplina);
		
		Relacao relacao = rer.findByTurmaAndDisciplina(turmaEnt, disciplinaEnt);
		rer.delete(relacao);
		
		model.addAttribute("sucess", "Turma Removida!");
		return new ModelAndView("redirect:/turmas/editar-turma?turma=" + turma, model) ;
		}
	
	@GetMapping("resultado-turma")
	public String resultado(@RequestParam(name = "ano") String ano,
							@RequestParam(name = "trimestre") String trimestre,
							@RequestParam(name = "turma") String turma,
							Model model) {
		
		Turma turmaEnt = tr.findByCodigo(turma);
		List<Resultado> resultados = rr.findByEverything(Integer.parseInt(ano),
				Trimestre.valueOf(trimestre), turmaEnt);
		model.addAttribute("ano", ano);
		model.addAttribute("turma", turmaEnt);
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		model.addAttribute("competencias", cr.findAll());
		model.addAttribute("resultados", resultados);
		return "site/resultado-turma";
		
	}
	
	@GetMapping("resultado")
	public String resultado(Model model,@RequestParam(name="turma") String turma) {
		Turma turmaEnt = tr.findByCodigo(turma);
		List<Resultado> resultados = rr.findByTurma(turmaEnt);
		List<Integer> anos = new ArrayList<>();
		for(Resultado resultado: resultados) {
			if(!anos.contains(resultado.getData().getYear())) {
				anos.add(resultado.getData().getYear());
			}
		}
		model.addAttribute("turma", turmaEnt);
		model.addAttribute("resultadoObj", new PegarResultado(rr));
		model.addAttribute("trimestre", tar.getTrimestreAtual());
		model.addAttribute("anos", anos);
		model.addAttribute("resultados", resultados);
		return "site/resultados";
	}
	
	@PostMapping("fechar-trimestre")
	public ModelAndView fecharTrimestre(ModelMap model) {
		TrimestreDatabase td = tar.getTrimestreAtual();
		
		if(td.getTrimestre() == Trimestre.TERCEIRO) {
			tar.fecharTrimestre();
			model.addAttribute("sucess", "Trimestre Fechado!");
			return new ModelAndView("redirect:/turmas", model);
		}
		
		tar.trocarTrimestre(Trimestre.getTrimestre(td.getTrimestre().getId() + 1));
		model.addAttribute("sucess", "Trimestre Fechado!");
		return new ModelAndView("redirect:/turmas", model);
	}
}
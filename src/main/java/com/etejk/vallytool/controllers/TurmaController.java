package com.etejk.vallytool.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.repositories.RelacaoRepository;
import com.etejk.vallytool.repositories.ResultadoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;

@Controller
@RequestMapping("turmas")
public class TurmaController {
	
	@Autowired
	TurmaRepository tr;
	
	@Autowired
	ResultadoRepository rr;
	
	@PostMapping("")
	public String saveTurma(@Valid Turma turma) {
		if(tr.findByCodigo(turma.getCodigo()) != null) {
			return "redirect:/turmas/error";
		}
		
		System.out.println(turma.getCodigo());
		tr.save(turma);
		return "redirect:/turmas";
	}
	
	@GetMapping("/error")
	public ModelAndView turmaError(ModelMap model) {
		model.addAttribute("error", "Turma já existe!");
		return new ModelAndView("redirect:/turmas", model);
	}
	
	@GetMapping("")
	public String turmas(Model model, @Param("search") String search) {
		if(search != null) {
		model.addAttribute("turmas", tr.search(search));
		}else {
			model.addAttribute("turmas", tr.findAll());
		}
		
		return "site/turmas";
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
		model.addAttribute("anos", anos);
		model.addAttribute("resultados", resultados);
		return "site/resultados";
	}
	
	@GetMapping("editar-turma")
	public String editarTurma() {
		return "site/editar-turma";
	}
}
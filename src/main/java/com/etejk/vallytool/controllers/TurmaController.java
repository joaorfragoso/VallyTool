package com.etejk.vallytool.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.repositories.TurmaRepository;

@Controller
@RequestMapping("turmas")
public class TurmaController {
	
	@Autowired
	TurmaRepository tr;
	
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
}

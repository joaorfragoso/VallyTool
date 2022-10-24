package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.repositories.TurmaRepository;

@Controller
public class TurmaController {
	
	@Autowired
	TurmaRepository tr;
	
	@PostMapping("turma")
	public String saveTurma(Turma turma) {
		if(tr.findByCodigo(turma.getCodigo()) != null) {
			return "redirect:/turma_erro";
		}
		tr.save(turma);
		return "redirect:/turma_error";
	}
	
	@GetMapping("turma_error")
	public ModelAndView turmaError(ModelMap model) {
		model.addAttribute("error", "turma já existe");
		return new ModelAndView("redirect:/turma", model);
	}
	
	@GetMapping("turmas")
	public String turmas(Model model, @Param("search") String search) {
		if(search != null) {
		model.addAttribute("turmas", tr.search(search));
		}else {
			model.addAttribute("turmas", tr.findAll());
		}
		
		return "site/turmas";
	}
}

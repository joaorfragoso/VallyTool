package com.etejk.vallytool.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;

@Controller
public class AvaliarController {
	
	@Autowired
	CompetenciaRepository cr;
	
	@Autowired
	ProfessoRepository pr;
	
	@GetMapping("avaliar")
	public String avaliar(Model model, Authentication auth) {
		model.addAttribute("competencias", cr.findAll());
		model.addAttribute("turmas", pr.findById(pr.findByNome(auth.getName()).getId()).get().getTurmas());
		model.addAttribute("disciplinas", pr.findById(pr.findByNome(auth.getName()).getId()).get().getDisciplinas());
		model.addAttribute("usuario", auth);
		return "site/avaliar_turma";
	}
}

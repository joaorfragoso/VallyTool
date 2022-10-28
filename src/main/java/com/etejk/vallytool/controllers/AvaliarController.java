package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.CompetenciaRepository;
import com.etejk.vallytool.repositories.ProfessoRepository;
import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.services.TurmaService;

@Controller
public class AvaliarController {
	
	@Autowired
	CompetenciaRepository cr;
	
	@Autowired
	ProfessoRepository pr;
	
	@Autowired
	TurmaRepository tr;

	@GetMapping("avaliar")
	public String avaliar(Model model, Authentication auth) {
		Usuario usuario = pr.findByNome(auth.getName());
		model.addAttribute("competencias", cr.findAll());
		model.addAttribute("turmas", usuario.getTurmas());
		model.addAttribute("disciplinas", usuario.getDisciplinas());
		model.addAttribute("usuario", usuario);
		return "site/avaliar_turma";
	}
	
	@GetMapping("avaliar_error")
	public ModelAndView avaliarErro(ModelMap model) {
		model.addAttribute("error", "Avaliação já foi realizada!");
		return new ModelAndView("redirect:/avaliar", model);
	}
}

package com.etejk.vallytool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
	@PostMapping("usuario")
	public String saveUsuario() {
		return "redirect:/inicio";
	}
}


package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etejk.vallytool.repositories.UsuarioRepository;

@Controller
public class RedefinirController {
	
	@Autowired
	UsuarioRepository ur;
	
	@GetMapping("redefinir")
	public String redefinir() {
		return "site/redefinir_senha";
	}
	
	@PostMapping("redefinir")
	public String verificarCpf(@RequestParam("cpf") String cpf) {
		String cpfParsed = cpf.replace("-", "").replace(".", "");
		try { 
		ur.findByCpf(cpfParsed);
		}catch(Exception e) {
			System.out.println("erro");
		}
		
		return "";
		
		
	}
}

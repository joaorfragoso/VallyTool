package com.etejk.vallytool.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etejk.vallytool.dto.RequisicaoNovoUsuario;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.services.RegisterService;

@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService register;
	
	@PostMapping("/register")
	public String registerUser(@Valid RequisicaoNovoUsuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		return register.registerUsuario(usuario);
	}
}

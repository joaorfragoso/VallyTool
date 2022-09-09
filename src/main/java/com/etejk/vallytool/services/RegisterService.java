package com.etejk.vallytool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.etejk.vallytool.dto.RequisicaoNovoUsuario;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.RegisterRepository;

@Service
public class RegisterService {
	
	@Autowired
	private RegisterRepository repository;
	
	public String registerUsuario(RequisicaoNovoUsuario reqUsuario) {
		Usuario usuario = reqUsuario.toUsuario();
		repository.save(usuario);
		
		return "redirect:/api/usuarios";
	}
}

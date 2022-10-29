package com.etejk.vallytool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository ur;
	
	
	public void changeUserPassword(Usuario user, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setSenha(encoder.encode(password));
		System.out.println(user.getSenha());
		ur.save(user);
	}
}

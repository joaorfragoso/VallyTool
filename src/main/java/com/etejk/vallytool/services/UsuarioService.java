package com.etejk.vallytool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Page<Usuario> findUsuarios(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Page<Usuario> findUsuarios(Character cargo, Pageable pageable) {
		return repository.findUsuarios(cargo, pageable);
	}
	
	public List<Usuario> findUsuarios(){
		return repository.findAll();
	}
}

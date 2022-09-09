package com.etejk.vallytool.repositories;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("SELECT obj.id, obj.nome FROM Usuario obj WHERE cargo = :cargo")
	Page<Usuario> findUsuarios(Character cargo, Pageable pageable);
	
}

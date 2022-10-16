package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;

public interface ProfessoRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByNome(String nome);
}

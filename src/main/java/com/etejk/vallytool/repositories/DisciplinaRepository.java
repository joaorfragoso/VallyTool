package com.etejk.vallytool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etejk.vallytool.entities.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
	Disciplina findByNome(String nome);
}

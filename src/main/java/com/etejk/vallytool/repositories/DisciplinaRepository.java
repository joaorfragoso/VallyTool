package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
	Disciplina findByNome(String nome);
}

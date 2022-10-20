package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{
	Turma findByCodigo(String codigo);
}

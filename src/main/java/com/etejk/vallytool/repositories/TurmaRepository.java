package com.etejk.vallytool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{
	
	@Query("SELECT u FROM Turma u WHERE u.codigo LIKE %?1%")
	List<Turma> search(String search);
	
	Turma findByCodigo(String codigo);
}

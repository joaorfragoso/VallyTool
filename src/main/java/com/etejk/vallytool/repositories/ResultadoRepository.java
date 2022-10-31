package com.etejk.vallytool.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{
	List<Resultado> findByData(LocalDate data);
	
	@Query("SELECT r FROM Resultado r WHERE YEAR(r.data) = ?1 AND r.trimestre = ?2")
	List<Resultado> findAnoTrimestre(Integer ano, Trimestre trimestre);
	
	@Query("SELECT r FROM Resultado r WHERE YEAR(r.data) = ?1 AND r.trimestre = ?2 AND r.turma = ?3")
	List<Resultado> findByEverything(Integer ano, Trimestre trimestre, Turma turma);
	List<Resultado> findByTrimestre(Trimestre trimestre);
	
	List<Resultado> findByTurma(Turma turma);
	
}

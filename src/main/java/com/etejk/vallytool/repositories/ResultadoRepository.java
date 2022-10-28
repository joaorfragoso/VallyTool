package com.etejk.vallytool.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{
	List<Resultado> findByData(LocalDate data);
	
	@Query("SELECT r FROM Resultado r WHERE YEAR(r.data) = ?1 AND r.trimestre = ?2")
	List<Resultado> findAnoTrimestre(Integer ano, Trimestre trimestre);
	List<Resultado> findByTrimestre(Trimestre trimestre);
	
}

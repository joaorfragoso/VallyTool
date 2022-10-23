package com.etejk.vallytool.repositories;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{
	List<Resultado> findByAno(Year ano);
	List<Resultado> findByTrimestre(Trimestre trimestre);
}

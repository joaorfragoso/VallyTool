package com.etejk.vallytool.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Resultado;
import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{
	List<Resultado> findByData(LocalDate data);
	
	List<Resultado> findByUsuario(Usuario usuario);
	
	@Query("SELECT r FROM Resultado r WHERE YEAR(r.data) = ?1 AND r.trimestre = ?2")
	List<Resultado> findAnoTrimestre(Integer ano, Trimestre trimestre);
	
	@Query("SELECT r FROM Resultado r WHERE YEAR(r.data) = ?1 AND r.trimestre = ?2 AND r.turma = ?3")
	List<Resultado> findByEverything(Integer ano, Trimestre trimestre, Turma turma);
	
	@Query("SELECT r FROM Resultado r WHERE YEAR(r.data) = ?1 AND r.trimestre = ?2 AND r.turma = ?3 AND r.usuario = ?4 AND r.disciplina = ?5")
	Resultado findByEverything(Integer ano, Trimestre trimestre, Turma turma, Usuario usuario, Disciplina disciplina);
	List<Resultado> findByTrimestre(Trimestre trimestre);
	
	List<Resultado> findByTurma(Turma turma);
	
}

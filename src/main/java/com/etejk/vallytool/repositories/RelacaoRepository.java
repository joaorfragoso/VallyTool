package com.etejk.vallytool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Disciplina;
import com.etejk.vallytool.entities.Relacao;
import com.etejk.vallytool.entities.Turma;
import com.etejk.vallytool.entities.Usuario;

@Repository
public interface RelacaoRepository extends JpaRepository<Relacao, Integer> {
	List<Relacao> findByDisciplina(Disciplina disciplina);
	List<Relacao> findByTurma(Turma turma);
	List<Relacao> findByUsuario(Usuario usuario);
	
	@Query("SELECT r FROM Relacao r WHERE r.turma = ?1 AND r.disciplina = ?2")
	List<Relacao> findByTurmaAndDisciplina(Turma turma, Disciplina disciplina);
	
	@Query("SELECT r FROM Relacao r WHERE r.turma = ?1 AND r.usuario = ?2")
	List<Relacao> findByTurmaAndUsuario(Turma turma, Usuario usuario);
	
	@Query("SELECT r FROM Relacao r WHERE r.turma = ?1 AND r.disciplina = ?2 AND r.usuario = ?3")
	Relacao findByEverything(Turma turma,Disciplina disciplina, Usuario usuario);
}

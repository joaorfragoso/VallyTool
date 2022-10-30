package com.etejk.vallytool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
}

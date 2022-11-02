package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.TrimestreDatabase;

@Repository
@Transactional
public interface TrimestreAtualRepository extends JpaRepository<TrimestreDatabase, Integer>{
	@Query("SELECT t FROM TrimestreDatabase t WHERE t.id = 1")
	TrimestreDatabase getTrimestreAtual();
	
	@Modifying
	@Query("UPDATE TrimestreDatabase t SET t.trimestre = ?1, t.aberto = true WHERE t.id = 1")
	void trocarTrimestre(Trimestre trimestre);
	
	@Modifying
	@Query("UPDATE TrimestreDatabase t SET t.aberto = false WHERE t.id = 1")
	void fecharTrimestre();
	
	@Modifying
	@Query("UPDATE TrimestreDatabase t SET t.aberto = true WHERE t.id = 1")
	void abrirTrimestre();
	
	@Modifying
	@Query("UPDATE TrimestreDatabase t SET t.aberto = true, t.trimestre = 'PRIMEIRO'")
	void resetar();
}

package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Trimestre;
import com.etejk.vallytool.entities.TrimestreDatabase;

@Repository
public interface TrimestreAtualRepository extends JpaRepository<TrimestreDatabase, Integer>{
	@Query("SELECT t FROM TrimestreDatabase t WHERE t.id = 1")
	TrimestreDatabase getTrimestreAtual();
	
	@Query("UPDATE TrimestreDatabase t SET t.trimestre = ?1, t.aberto = true WHERE t.id = 1")
	void trocarTrimestre(Trimestre trimestre);
	
	@Query("UPDATE TrimestreDatabase t SET t.aberto = false WHERE t.id = 1")
	void fecharTrimestre();
	
	@Query("UPDATE TrimestreDatabase t SET t.aberto = true WHERE t.id = 1")
	void abrirTrimestre();
}

package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Relacao;

@Repository
public interface RelacaoRepository extends JpaRepository<Relacao, Integer> {

}

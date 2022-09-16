package com.etejk.vallytool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Usuario;

@Repository
public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer>{
	Usuario findByCpf(String cpf);
}

package com.etejk.vallytool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByCpf(String cpf);
	
	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1% ORDER BY u.nome ASC")
	List<Usuario> search(String search);
	
	Usuario findByNome(String nome);
}

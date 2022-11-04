package com.etejk.vallytool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.PasswordResetToken;
import com.etejk.vallytool.entities.Usuario;

@Repository
public interface ResetRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
	
	List<PasswordResetToken> findByUsuario(Usuario usuario);
}

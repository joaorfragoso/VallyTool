package com.etejk.vallytool.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etejk.vallytool.entities.PasswordResetToken;
import com.etejk.vallytool.entities.Usuario;
import com.etejk.vallytool.repositories.ResetRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;

@Service
public class ResetService {

	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private ResetRepository rr;
	
	public void createPasswordToken(String token, Usuario usuario) {
		PasswordResetToken prt = new PasswordResetToken(token, usuario);
		rr.save(prt);
		
	}
	
	public String validatePasswordToken(String token) {
		final PasswordResetToken passToken = rr.findByToken(token);
		if(passToken == null) {
			return "Token Inválido";
		}else if(isTokenExpired(passToken)) {
			return "Token Expirado";
		}
		
		return null;
	}
	
	public boolean isTokenExpired(PasswordResetToken passToken) {
		final Calendar cal = Calendar.getInstance();
		System.out.println(passToken.getExpiryDate().toString());
		System.out.println(cal.getTime().toString());
		if(passToken.getExpiryDate().before(cal.getTime())) {
			return true;
		}
		
		return false;
	}
	
	public Usuario findUserByToken(String token) {
		return rr.findByToken(token).getUsuario();
	}
}

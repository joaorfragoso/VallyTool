package com.etejk.vallytool.dao;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioDAO {
	public String nome;
	public String cpf;
	public String email;
	public String role;
	
	public String senha() {
		return new BCryptPasswordEncoder().encode(cpf.replace(".", "").replace("-", ""));
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UsuarioDAO [nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", role=" + role + "]";
	}
	
	
}

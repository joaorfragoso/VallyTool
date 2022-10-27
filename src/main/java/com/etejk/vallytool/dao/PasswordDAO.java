package com.etejk.vallytool.dao;

public class PasswordDAO {
	public String senha;
	public String token;
	
	public PasswordDAO() {
		
	}

	public PasswordDAO(String senha, String token) {
		super();
		this.senha = senha;
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}

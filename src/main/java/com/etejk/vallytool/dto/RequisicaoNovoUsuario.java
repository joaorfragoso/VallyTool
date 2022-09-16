package com.etejk.vallytool.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.etejk.vallytool.entities.Usuario;

public class RequisicaoNovoUsuario {
	
	@NotBlank
	private String cpf;
	@NotBlank
	private String senha;
	@NotBlank
	private String nome;
	private Character cargo;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf.replace(".", "").replace("-", "");
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Character getCargo() {
		return cargo;
	}
	public void setCargo(Character cargo) {
		this.cargo = cargo;
	}
	public Usuario toUsuario() {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario.setCpf(this.cpf);
		usuario.setCargo(this.cargo);
		usuario.setNome(this.nome);
		usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
		return usuario;
	}
}

package com.etejk.vallytool.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected Integer id;
	
	protected String cpf;
	private String senha;
	
	protected String nome;
	protected Character cargo;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String cpf, String senha, Character cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.cargo = cargo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
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


	public static void realizarLogin(Usuario usuario) {
		
	}
	
}

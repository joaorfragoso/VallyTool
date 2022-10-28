package com.etejk.vallytool.entities;

import java.io.Serializable;



import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false, unique = true)
	private String nome;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String senha;
	@ManyToMany
	private List<RoleModel> roles;
	@ManyToMany
	private List<Turma> turmas;
	@ManyToMany
	private List<Disciplina> disciplinas;
	
	public Usuario() {}
	
	
	public Usuario(String cpf, String nome, String email, String senha, List<RoleModel> roles) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.roles = roles;
	}


	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getCpf() {
		String cpf[] = this.cpf.split("");
		String novoCpf = "";
		for (int i = 0; i < cpf.length; i++) {
			novoCpf += cpf[i];
			if (i == 2 || i == 5) {
				novoCpf += ".";
				continue;
			}
			if (i == 8) {
				novoCpf += "-";
				continue;
			}
		}
		return novoCpf;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	public List<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}
	
	public String getAuthority() {
		String role = roles.get(0).getRoleName().name();
		if(role.equals("ROLE_PROFESSOR")) {
			return "Professor";
		}
		return "SOP";
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

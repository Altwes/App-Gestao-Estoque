package com.almoxarifado.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
	
@SuppressWarnings("serial")
@Entity
@Table(name = "USUARIOS")
public class Usuario  extends AbstractEntity<Long> implements UserDetails{
	
	@Column(nullable = false, unique = true, length = 100)
	private String login;
	
	@Column(nullable = false, unique = true, length = 100)
	private String senha;
	
	@Column(nullable = false, length = 255)
	private String nome;
	 
	@Column(nullable = false, unique = true, length = 255)
	private String email;

	@CPF
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Column(nullable = false)
	private boolean status;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
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
		return this.isStatus();
	}
	
	public String getName() {
		return this.getNome();
	}
	
	public Long getIds() {
		return this.getId();
	}
	
	
	
}


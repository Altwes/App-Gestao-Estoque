package com.almoxarifado.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUTOS")
public class Produto extends AbstractEntity<Long>{
				
	@Column(name = "descricao", nullable = false, unique = true, length = 255)
	private String descricao;
	
	@Column(nullable = false)
	private boolean status;
		
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}


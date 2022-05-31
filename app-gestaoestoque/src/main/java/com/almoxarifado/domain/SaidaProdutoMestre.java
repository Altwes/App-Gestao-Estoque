package com.almoxarifado.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SAIDA_MATERIAIS_MESTRE")
public class SaidaProdutoMestre extends AbstractEntity<Long> {
	
	@Column(name = "data_saida", nullable = false)
	private Date dataSaida = new Date();
	
	@Column
	private String observacoes;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "mestre_id")
	private List<SaidaProdutoDetalhe> detalhe;
	
	public SaidaProdutoMestre() {
		this.detalhe = new ArrayList<SaidaProdutoDetalhe>();
	}

	public SaidaProdutoMestre(Date dataSaida, String observacoes, Usuario usuario, List<SaidaProdutoDetalhe> detalhe) {
		super();
		this.dataSaida = dataSaida;
		this.observacoes = observacoes;
		this.usuario = usuario;
		this.detalhe = detalhe;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SaidaProdutoDetalhe> getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(List<SaidaProdutoDetalhe> detalhe) {
		this.detalhe = detalhe;
	}

}

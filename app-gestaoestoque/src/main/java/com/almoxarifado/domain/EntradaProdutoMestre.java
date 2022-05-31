package com.almoxarifado.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENTRADA_MATERIAIS_MESTRE")
public class EntradaProdutoMestre extends AbstractEntity<Long>{
	
	@Column(name = "data_entrada", nullable = false)
	private Date dataEntrada = new Date();
	
	@Column
	private String Observacoes;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "mestre_id")
	private List<EntradaProdutoDetalhe> detalhes;

	public EntradaProdutoMestre() {
		this.detalhes = new ArrayList<EntradaProdutoDetalhe>();
	}
	
	public EntradaProdutoMestre(Date dataEntrada, String observacoes, Usuario usuario, List<EntradaProdutoDetalhe> detalhes) {
		super();
		this.dataEntrada = dataEntrada;
		Observacoes = observacoes;
		this.usuario = usuario;
		this.detalhes = detalhes;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getObservacoes() {
		return Observacoes;
	}

	public void setObservacoes(String observacoes) {
		Observacoes = observacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<EntradaProdutoDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<EntradaProdutoDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	
}
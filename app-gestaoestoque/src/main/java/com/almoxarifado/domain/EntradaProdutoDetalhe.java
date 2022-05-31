package com.almoxarifado.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENTRADA_MATERIAIS_DETALHE")
public class EntradaProdutoDetalhe extends AbstractEntity<Long>{
	
	@OneToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Column
	private int quantidade;
	
	@ManyToOne
	private EntradaProdutoMestre mestre;
	
	public EntradaProdutoDetalhe() {
	}

	public EntradaProdutoDetalhe(Produto produto, int quantidade, EntradaProdutoMestre mestre) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.mestre = mestre;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public EntradaProdutoMestre getMestre() {
		return mestre;
	}

	public void setMestre(EntradaProdutoMestre mestre) {
		this.mestre = mestre;
	}
	
	
}

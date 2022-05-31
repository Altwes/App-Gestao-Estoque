package com.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.almoxarifado.domain.Produto;
import com.almoxarifado.repository.ProdutoRepository;

@Service
public class ProdutoService {
	

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional(readOnly = false)
	public void salvar(Produto produto) {
		produtoRepository.save(produto);
	}
	
	@Transactional(readOnly = false)
	public void editar(Produto produto) {
		produtoRepository.save(produto);
	}
	
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}
	
	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).get();
	}

	public List<Produto> buscarTodos() {		
		return produtoRepository.findAll();
	}

}

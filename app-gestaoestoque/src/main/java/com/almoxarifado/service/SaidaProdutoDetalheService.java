package com.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almoxarifado.domain.SaidaProdutoDetalhe;
import com.almoxarifado.repository.SaidaProdutoDetalheRepository;

@Service
public class SaidaProdutoDetalheService {
	
	@Autowired
	private SaidaProdutoDetalheRepository saidaProdutoDetalheRepository;
	
	public void salvar(SaidaProdutoDetalhe detalhe) {
		saidaProdutoDetalheRepository.save(detalhe);
	}
	
	public void editar(SaidaProdutoDetalhe detalhe) {
		saidaProdutoDetalheRepository.save(detalhe);
	}
	
	public void excluir(Long id) {
		saidaProdutoDetalheRepository.deleteById(id);
	}
	
	public SaidaProdutoDetalhe buscarPorId(Long id) {
		return saidaProdutoDetalheRepository.findById(id).get();
	}
	
	public List<SaidaProdutoDetalhe> buscarTodos() {
		return saidaProdutoDetalheRepository.findAll();
	}
}

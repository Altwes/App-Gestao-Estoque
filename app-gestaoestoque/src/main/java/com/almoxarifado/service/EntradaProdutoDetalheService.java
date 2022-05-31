package com.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almoxarifado.domain.EntradaProdutoDetalhe;
import com.almoxarifado.repository.EntradaProdutoDetalheRepository;

@Service
public class EntradaProdutoDetalheService {

	@Autowired
	private EntradaProdutoDetalheRepository entradaProdutoDetalheRepository;
	
	public void salvar(EntradaProdutoDetalhe detalhe) {
		entradaProdutoDetalheRepository.save(detalhe);
	}
	
	public void editar(EntradaProdutoDetalhe  detalhe) {
		entradaProdutoDetalheRepository.save(detalhe);
	}
	
	public void excluir(Long id) {
		entradaProdutoDetalheRepository.deleteById(id);
	}
	
	public EntradaProdutoDetalhe buscarPorId(Long id) {
		return entradaProdutoDetalheRepository.findById(id).get();
	}

	public List<EntradaProdutoDetalhe> buscarTodos() {		
		return entradaProdutoDetalheRepository.findAll();
	}
}

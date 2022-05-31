package com.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almoxarifado.domain.EntradaProdutoMestre;
import com.almoxarifado.repository.EntradaProdutoMestreRepository;

@Service 
public class EntradaProdutoMestreService {
	
	@Autowired
	private EntradaProdutoMestreRepository entradaProdutoMestreRepository;
	
	public void salvar(EntradaProdutoMestre entrada) {
		entradaProdutoMestreRepository.save(entrada);
	}
	
	public void editar(EntradaProdutoMestre entrada) {
		entradaProdutoMestreRepository.save(entrada);
	}
	
	public void excluir(Long id) {
		entradaProdutoMestreRepository.deleteById(id);
	}
	
	public EntradaProdutoMestre buscarPorId(Long id) {
		return entradaProdutoMestreRepository.findById(id).get();
	}

	public List<EntradaProdutoMestre> buscarTodos() {		
		return entradaProdutoMestreRepository.findAll();
	}
	
}

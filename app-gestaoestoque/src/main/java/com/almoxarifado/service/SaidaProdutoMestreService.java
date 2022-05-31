package com.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almoxarifado.domain.SaidaProdutoMestre;
import com.almoxarifado.repository.SaidaProdutoMestreRepository;

@Service
public class SaidaProdutoMestreService {

	@Autowired
	private SaidaProdutoMestreRepository saidaProdutoMestreRepository;
	
	public void salvar(SaidaProdutoMestre entrada) {
		saidaProdutoMestreRepository.save(entrada);
	}
	
	public void editar(SaidaProdutoMestre entrada) {
		saidaProdutoMestreRepository.save(entrada);
	}
	
	public void excluir(Long id) {
		saidaProdutoMestreRepository.deleteById(id);
	}
	
	public SaidaProdutoMestre buscarPorId(Long id) {
		return saidaProdutoMestreRepository.findById(id).get();		
	}
	
	public List<SaidaProdutoMestre> buscarTodos(){
		return saidaProdutoMestreRepository.findAll();
	}
	
}


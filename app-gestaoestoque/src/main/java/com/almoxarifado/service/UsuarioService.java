package com.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.almoxarifado.domain.Usuario;
import com.almoxarifado.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true)

public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly = false)
	public void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void editar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).get();
	}

	
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
}

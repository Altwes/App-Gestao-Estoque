package com.almoxarifado.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almoxarifado.domain.Usuario;
import com.almoxarifado.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Usuario usuario) {
		return "usuarios/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("usuarios", service.buscarTodos());
		return "usuarios/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if(usuario.getNome().trim() == "") {
			attr.addFlashAttribute("erroNome", "Nome não pode ser vazio");
			return "redirect:/usuarios/cadastrar";
		}
		
		boolean verificadorEmail = false;
		boolean verificadorCpf = false;
		boolean verificadorLogin = false;
		
		String cpf = usuario.getCpf();
		usuario.setCpf( cpf.replace(".","").replace("-","") );
		
		for(Usuario user: service.buscarTodos()) {
			if(usuario.getEmail().toUpperCase().equals(user.getEmail().toUpperCase())) {
				verificadorEmail = true;
			}
			if(usuario.getCpf().equals(user.getCpf())) {
				verificadorCpf = true;
			}
			if(usuario.getLogin().toUpperCase().equals(user.getLogin().toUpperCase())) {
				verificadorLogin = true;
			}
		}
		
		if(result.hasFieldErrors("cpf")) {
			attr.addFlashAttribute("erroNome", "CPF invalido");
			return "redirect:/usuarios/cadastrar";
		}
		
		if(verificadorEmail == true) {
			attr.addFlashAttribute("erroNome", "E-mail ja cadastrado");
			return "redirect:/usuarios/cadastrar";
		}
		
		if(verificadorCpf == true) {
			attr.addFlashAttribute("erroNome", "CPF ja cadastrado");
			return "redirect:/usuarios/cadastrar";
		}
		
		if(verificadorLogin == true) {
			attr.addFlashAttribute("erroNome", "Login ja cadastrado");
			return "redirect:/usuarios/cadastrar";
		}
		
		BCryptPasswordEncoder code = new BCryptPasswordEncoder();
		usuario.setSenha(code.encode(usuario.getSenha()));
		
		usuario.setNome(usuario.getNome().toUpperCase());
		service.salvar(usuario);
		attr.addFlashAttribute("sucesso", "O usuario foi cadastrado com sucesso");
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		service.excluir(id);
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute( "usuario", service.buscarPorId(id) );
		return "usuarios/editar";
	}
	
	@PostMapping("/editar")
	public String editar (@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if(usuario.getNome().trim() == "") {
			attr.addFlashAttribute("erroNome", "Nome não pode ser vazio");
			return "redirect:/usuarios/cadastrar";
		}
		
		List<Usuario> list = service.buscarTodos();
		list.remove(service.buscarPorId(usuario.getId()));
		
		boolean verificadorEmail = false;
		boolean verificadorCpf = false;
		boolean verificadorLogin = false;
		
		String cpf = usuario.getCpf();
		usuario.setCpf( cpf.replace(".","").replace("-","") );
		
		for(Usuario user: list) {
			if(usuario.getEmail().toUpperCase().equals(user.getEmail().toUpperCase())) {
				verificadorEmail = true;
			}
			if(usuario.getCpf().equals(user.getCpf())) {
				verificadorCpf = true;
			}
			if(usuario.getLogin().toUpperCase().equals(user.getLogin().toUpperCase())) {
				verificadorLogin = true;
			}
		}
		
		if(result.hasFieldErrors("cpf")) {
			attr.addFlashAttribute("erroNome", "CPF invalido");
			return "redirect:/usuarios/cadastrar";
		}
		
		if(verificadorEmail == true) {
			attr.addFlashAttribute("erroNome", "E-mail ja cadastrado");
			return "redirect:/usuarios/cadastrar";
		}
		
		if(verificadorCpf == true) {
			attr.addFlashAttribute("erroNome", "CPF ja cadastrado");
			return "redirect:/usuarios/cadastrar";
		}
		
		if(verificadorLogin == true) {
			attr.addFlashAttribute("erroNome", "Login ja cadastrado");
			return "redirect:/usuarios/cadastrar";
		}
		
		BCryptPasswordEncoder code = new BCryptPasswordEncoder();
		usuario.setSenha(code.encode(usuario.getSenha()));
		usuario.setNome(usuario.getNome().toUpperCase());
		service.editar(usuario);
		attr.addFlashAttribute("sucesso", "O usuario foi editado com sucesso");
		return "redirect:/usuarios/listar";
	}
	
}

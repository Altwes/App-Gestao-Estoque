package com.almoxarifado.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almoxarifado.domain.Produto;
import com.almoxarifado.service.EntradaProdutoDetalheService;
import com.almoxarifado.service.EntradaProdutoMestreService;
import com.almoxarifado.service.ProdutoService;
import com.almoxarifado.service.SaidaProdutoDetalheService;
import com.almoxarifado.service.SaidaProdutoMestreService;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoService service;
	
	@Autowired
	private EntradaProdutoMestreService entradaProdutoMestreService;
	
	@Autowired
	private EntradaProdutoDetalheService entradaProdutoDetalheService;
	
	@Autowired
	private SaidaProdutoMestreService saidaProdutoMestreService;
	
	@Autowired
	private SaidaProdutoDetalheService saidaProdutoDetalheService;
		
	@GetMapping("/cadastrar")
	public String cadastrar(Produto produto) {
		
		return "produtos/cadastro";
	}
	
	@GetMapping("/relatorio")
	public String relatorio(Model model) {
		model.addAttribute("produtos", service.buscarTodos());
		return "produtos/relatorios";
	}
	
	/*botao relatorio da pagina relatorio*/
	@GetMapping("/relatorioprod/{id}")
	public String relatorioprod(@PathVariable Long id, ModelMap model) {
		model.addAttribute("produtos", service.buscarPorId(id));
		
		model.addAttribute("entradaMestre", entradaProdutoMestreService.buscarTodos());
		model.addAttribute("entradaDetalhe", entradaProdutoDetalheService.buscarTodos());
		
		model.addAttribute("saidaMestre", saidaProdutoMestreService.buscarTodos());
		model.addAttribute("saidaDetalhe", saidaProdutoDetalheService.buscarTodos());
		
		return "produtos/relatorioprod";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("produtos", service.buscarTodos());
		return "produtos/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
		
		if(produto.getDescricao().trim() == "") {
			attr.addFlashAttribute("erroNome", "Produto não pode ser vazio");
			return "redirect:/produtos/cadastrar";
		}
		
		boolean verificador = false;
		
		for(Produto prod: service.buscarTodos()) {
			if(produto.getDescricao().toUpperCase().equals(prod.getDescricao().toUpperCase())) {
				verificador = true;
			}
		}
		
		if(verificador == true) {
			attr.addFlashAttribute("erroNome", "Produto já cadastrado");
			return "redirect:/produtos/cadastrar";
		}
		
		produto.setDescricao(produto.getDescricao().toUpperCase());
		service.salvar(produto);
		attr.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return "redirect:/produtos/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		service.excluir(id);
		return "redirect:/produtos/listar";
	}	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute( "produto", service.buscarPorId(id) );
		return "produtos/editar";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
		
		if(produto.getDescricao().trim() == "") {
			attr.addFlashAttribute("erroNome", "Nome não pode ser vazio");
			return "redirect:/produtos/cadastrar";
		}
		
		List<Produto> list = service.buscarTodos();
		list.remove(service.buscarPorId(produto.getId()));
		
		boolean verificador = false;
		
		for(Produto prod: list) {
			if(produto.getDescricao().toUpperCase().equals(prod.getDescricao().toUpperCase())) {
				verificador = true;
			}
		}
		
		if(verificador == true) {
			attr.addFlashAttribute("erroNome", "Produto já cadastrado");
			return "redirect:/produtos/cadastrar";
		}
		
		produto.setDescricao(produto.getDescricao().toUpperCase());
		service.editar(produto);
		attr.addFlashAttribute("sucesso", "Produto editado com sucesso");
		return "redirect:/produtos/listar";
	}
}
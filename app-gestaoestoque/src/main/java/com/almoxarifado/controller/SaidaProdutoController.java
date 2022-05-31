package com.almoxarifado.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almoxarifado.domain.SaidaProdutoDetalhe;
import com.almoxarifado.domain.SaidaProdutoMestre;
import com.almoxarifado.repository.SaidaProdutoMestreRepository;
import com.almoxarifado.service.ProdutoService;
import com.almoxarifado.service.SaidaProdutoDetalheService;
import com.almoxarifado.service.SaidaProdutoMestreService;

@Controller
@RequestMapping("/saida")
public class SaidaProdutoController {
	
	@Autowired
	private SaidaProdutoMestreService saidaProdutoMestreService;
	
	@Autowired
	private SaidaProdutoMestreRepository saidaProdutoMestreRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private SaidaProdutoDetalheService saidaDetalheService;
	
	@GetMapping("/lista")
	public String saida(Model model) {
		model.addAttribute("produto", saidaProdutoMestreService.buscarTodos());
		return "produtos/saidaproduto";
	}
	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute ("saida") SaidaProdutoMestre saida, BindingResult result, RedirectAttributes attr, Model model) {
		SaidaProdutoMestre resposta = saidaProdutoMestreRepository.save(saida);
		return "redirect:/saida/editar/" + resposta.getId();
	}
	
	@GetMapping("/novo")
	public String adicionar(Model model) {
		SaidaProdutoMestre saida = new SaidaProdutoMestre();
		model.addAttribute("saida", saida);
		return "produtos/editarsaida";
	}
	
	
	//botao add detalhe
	/*
	@RequestMapping("editar/adicionardetalhe/{id}")
	public String adicionardetalhe(@PathVariable Long id, RedirectAttributes att, SaidaProdutoDetalhe novodetalhe) {
		att.addFlashAttribute("produtos", produtoService.buscarTodos());
		SaidaProdutoMestre saida = saidaProdutoMestreService.buscarPorId(id);
		List<SaidaProdutoDetalhe> detalhes = saida.getDetalhe();
		detalhes.add(novodetalhe);
		saida.setDetalhe(detalhes);
		@SuppressWarnings("unused")
		SaidaProdutoMestre resposta = saidaProdutoMestreRepository.save(saida);
		return "redirect:/saida/editar/" + id;
	}*/
	
	////
		/*@PostMapping("/editar/adicionardetalhe/{id}")
		public String salvar (@Valid SaidaProdutoDetalhe saida, BindingResult result, RedirectAttributes attr) {
			
			Integer quantidadeSaida = saida.getQuantidade();
			Integer totalEntradas = ;
			Integer totalSaidas = ;
			Integer totalDisponivel = totalEntradas - totalSaidas;
			
			if(saida.getQuantidade() >= totalEntradas ) {
				attr.addFlashAttribute("error", "Quantidade de Produtos insuficiente");
				return "redirect:/saida/editar/{id}";
			}
			
			else {
				return "redirect:/saida/editar/{id}";
			}
		}*/
		////
	
	@RequestMapping("editar/adicionardetalhe/{id}")
	public String adicionardetalhe(@Valid SaidaProdutoDetalhe saidaProdutoDetalhe, @PathVariable Long id, RedirectAttributes att, SaidaProdutoDetalhe novodetalhe) {
		
		att.addFlashAttribute("produtos", produtoService.buscarTodos());
		
		SaidaProdutoMestre saida = saidaProdutoMestreService.buscarPorId(id);
		List<SaidaProdutoDetalhe> detalhes = saida.getDetalhe();
		detalhes.add(novodetalhe);
		saida.setDetalhe(detalhes);		
		@SuppressWarnings("unused")
		SaidaProdutoMestre resposta = saidaProdutoMestreRepository.save(saida);
		return "redirect:/saida/editar/" + id;
	}

	///////////////////////////////
	
	/*
	@PostMapping("/adicionarsaida")
	public String salvar (@ModelAttribute ("saida") SaidaProdutoMestre saida) {
		saidaProdutoMestreService.salvar(saida);
		return "redirect:/saida/lista";
	}
	
	@GetMapping("/adicionarsaida")
	public String adicionarsaida(Model model) {
		SaidaProdutoMestre saida = new SaidaProdutoMestre();
		model.addAttribute("saida", saida);
		model.addAttribute("produtos", produtoService.buscarTodos());
		return "/produtos/adicionarsaida";
	}*/
	
	//////////////////////////////
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		saidaProdutoMestreService.excluir(id);
		return "redirect:/saida/lista";
	}
	
	@GetMapping("/excluirdetalhe/{id}")
	public String excluirdetalhe(@PathVariable Long id) {
		Long mestreId = saidaDetalheService.buscarPorId(id).getMestre().getId();
		saidaDetalheService.excluir(id);
		return "redirect:/saida/editar/" + mestreId;
	}	
	
	@RequestMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("produtos", produtoService.buscarTodos());
		model.addAttribute("saida", saidaProdutoMestreService.buscarPorId(id));
		model.addAttribute("novodetalhe", new SaidaProdutoDetalhe());
		return "produtos/editarsaida";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid SaidaProdutoMestre saida, BindingResult result, RedirectAttributes attr) {
		return "redirect:/produtos/listar";
	}
	
}
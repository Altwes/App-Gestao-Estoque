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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almoxarifado.domain.EntradaProdutoDetalhe;
import com.almoxarifado.domain.EntradaProdutoMestre;
import com.almoxarifado.repository.EntradaProdutoMestreRepository;
import com.almoxarifado.service.EntradaProdutoDetalheService;
import com.almoxarifado.service.EntradaProdutoMestreService;
import com.almoxarifado.service.ProdutoService;

@Controller
@RequestMapping("/entrada")
public class EntradaProdutoController {

	@Autowired
	private EntradaProdutoMestreService entradaProdutoMestreService;
	
	@Autowired
	private EntradaProdutoMestreRepository entradaProdutoMestreRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EntradaProdutoDetalheService entradaDetalheService;
	
	@GetMapping("/lista")
	public String entrada(Model model) {
		model.addAttribute("entradas", entradaProdutoMestreService.buscarTodos());
		return "produtos/entradaproduto";
	}
	
	/*PostMapping("/adicionar")
	public String salvar(Valid EntradaProdutoMestre entrada, BindingResult result, RedirectAttributes attr) {
		entrada.setId(entrada.getId());
		service.salvar(entrada);
		attr.addFlashAttribute("sucesso", "Entrada cadastrada com sucesso");
		return "redirect:/entrada/lista";
	}*/
	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute ("entrada") EntradaProdutoMestre entrada,  BindingResult result, RedirectAttributes attr, Model model) {
		EntradaProdutoMestre resposta = entradaProdutoMestreRepository.save(entrada);
		return "redirect:/entrada/editar/" + resposta.getId();
	}
	
	@GetMapping("/novo")
	public String adicionar(Model model) {
		EntradaProdutoMestre entrada = new EntradaProdutoMestre();
		model.addAttribute("entrada", entrada);
		model.addAttribute("produtos", produtoService.buscarTodos());
		entrada.getDetalhes().add(new EntradaProdutoDetalhe());
	//	EntradaProdutoDetalhe novodetalhe = new EntradaProdutoDetalhe();
	//	model.addAttribute("novodetalhe", novodetalhe);
	//  model.addAttribute("produtos", produtoService.buscarTodos());
		return "produtos/editarentrada";
	}
	
	@PostMapping("/novoCampo")
	public ModelAndView novoCampo(EntradaProdutoMestre entrada) {
		entrada.getDetalhes().add(new EntradaProdutoDetalhe());
		ModelAndView mav = new ModelAndView("produtos/meuteste.html");
		mav.addObject("entrada", entrada);
		mav.addObject("produtos", produtoService.buscarTodos());
		return mav;
	}
	
//	@PostMapping("/adicionardetalhe")
//	public String adicionardetalhe(Model model, @ModelAttribute("entrada") EntradaProdutoMestre entrada, @ModelAttribute ("novodetalhe") EntradaProdutoDetalhe novodetalhe) {
//		
//		model.addAttribute("produtos", produtoService.buscarTodos());
//		
//		List<EntradaProdutoDetalhe> detalhes = entrada.getDetalhes();
//		detalhes.add(novodetalhe);
//		entrada.setDetalhes(detalhes);
//
//		model.addAttribute("entrada", entrada);
//		
//		return "/produtos/editarentrada";
//	}
	
//botao add detalhe antigo
	@RequestMapping("editar/adicionardetalhe/{id}")
	public String adicionardetalhe(@Valid Model model, EntradaProdutoDetalhe entradaProdutoDetalhe, @PathVariable Long id, RedirectAttributes att,  EntradaProdutoDetalhe novodetalhe) {
		att.addFlashAttribute("produtos", produtoService.buscarTodos());
		EntradaProdutoMestre entrada = entradaProdutoMestreService.buscarPorId(id);
		List<EntradaProdutoDetalhe> detalhes = entrada.getDetalhes();
		detalhes.add(novodetalhe);
		entrada.setDetalhes(detalhes);
		@SuppressWarnings("unused")
		EntradaProdutoMestre resposta = entradaProdutoMestreRepository.save(entrada);
		return "redirect:/entrada/editar/" + id;	
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		entradaProdutoMestreService.excluir(id);
		return "redirect:/entrada/lista";
	}
	 
	@GetMapping("/excluirdetalhe/{id}")
	public String excluirdetalhe(@PathVariable Long id) {
		Long mestreId = entradaDetalheService.buscarPorId(id).getMestre().getId();
		entradaDetalheService.excluir(id);
		return "redirect:/entrada/editar/" + mestreId ;
	}
	
	@RequestMapping("/visualizar/{id}")
	public String preVisualizar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("entradas", entradaProdutoMestreService.buscarTodos());
		model.addAttribute("detalhes", entradaProdutoMestreService.buscarPorId(id).getDetalhes());
		return "produtos/entradaproduto";
	}
	
	@RequestMapping("/relatorio/{id}")
	public String relatorio(@PathVariable Long id, ModelMap model) {
		model.addAttribute("entrada", entradaProdutoMestreService.buscarPorId(id));
		return "entrada/relatorio";
	}
	
	@RequestMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model, EntradaProdutoMestre entrada) {
		model.addAttribute("produtos", produtoService.buscarTodos());
		model.addAttribute("entrada", entradaProdutoMestreService.buscarPorId(id));
		model.addAttribute("novodetalhe", new EntradaProdutoDetalhe());
		return "produtos/editarentrada";
	}
	
}
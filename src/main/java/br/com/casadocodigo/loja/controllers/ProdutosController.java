package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDao produtoDao;

	@RequestMapping("form")
	public String form(Model model) {
		
		model.addAttribute("tipos", TipoPreco.values());
		
		return "produtos/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String gravar(Produto produto, RedirectAttributes redirectAttributes) {

		System.out.println(produto);
		produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("sucesso","Produto cadastrado com sucesso!");

		return "redirect:produtos"; // redirecionados para requestMapping de produtos que chama o metodo listar(Model model)
	}

	@RequestMapping(method=RequestMethod.GET)
	public String listar(Model model) {

		List<Produto> produtos = produtoDao.listar();
		model.addAttribute("produtos", produtos);

		return "produtos/lista";
	}
}

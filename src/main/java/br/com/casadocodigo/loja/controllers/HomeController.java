package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Produto> produtos = produtoDao.listar();
		model.addAttribute("produtos", produtos);
		System.out.println("Entrando na Home da CDC");
		return "home";
	}
}

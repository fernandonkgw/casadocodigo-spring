package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	@RequestMapping("add")
	public String add(Integer produtoId, TipoPreco tipo) {
		
		return "redirect:/produtos";
	}
}

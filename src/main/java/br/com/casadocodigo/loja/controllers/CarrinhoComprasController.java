package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("add")
	public String add(Integer produtoId, TipoPreco tipo) {
		
		CarrinhoItem carrinhoItem = criaCarrinhoItem(produtoId, tipo);
		
		return "redirect:/produtos";
	}
	
	private CarrinhoItem criaCarrinhoItem(Integer produtoId, TipoPreco tipo) {
		
		Produto produto = produtoDao.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(tipo, produto);
		
		return carrinhoItem;
	}
}

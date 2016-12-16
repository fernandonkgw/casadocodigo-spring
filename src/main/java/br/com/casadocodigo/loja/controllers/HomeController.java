package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.daos.UsuarioDao;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView model = new ModelAndView("home");
		model.addObject("produtos", produtos);
		return model;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping("url-magica-jshfiuyesrouiewyofsdhjkfsdkgfkjdshfkjsdyroweuiyfrowe")
	public String urlMagicaMaluca() {
		Usuario usuario = new Usuario(); 
	    usuario.setNome("Admin");
	    usuario.setEmail("admin@gmail.com");
	    usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
	    usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
	    
	    usuarioDao.gravar(usuario);
	    
	    return "URL Mágica executada";
	}
}

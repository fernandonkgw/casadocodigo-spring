package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("form")
	public String form(Produto produto, Model model) {
		
		model.addAttribute("tipos", TipoPreco.values());
		
		return "produtos/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value="produtosHome", allEntries=true)
	public String gravar(MultipartFile sumario,  @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		
		if (result.hasErrors()) {
			return form(produto, model);
		}

		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("sucesso","Produto cadastrado com sucesso!");

		return "redirect:/produtos"; // redirecionados para requestMapping de produtos que chama o metodo listar(Model model)
	}

	@RequestMapping(method=RequestMethod.GET)
	public String listar(Model model) {

		List<Produto> produtos = produtoDao.listar();
		model.addAttribute("produtos", produtos);

		return "produtos/lista";
	}
	
	@RequestMapping("detalhe/{id}")
	public String detalhe(@PathVariable("id") int id, Model model) {
		
		Produto produto = produtoDao.find(id);
		model.addAttribute("produto", produto);
		
		return "produtos/detalhe";
	}

}

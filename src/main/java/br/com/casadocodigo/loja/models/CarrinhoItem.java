package br.com.casadocodigo.loja.models;

public class CarrinhoItem {

	private TipoPreco tipo;
	private Produto produto;

	public CarrinhoItem(TipoPreco tipo, Produto produto) {
		this.tipo = tipo;
		this.produto = produto;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}

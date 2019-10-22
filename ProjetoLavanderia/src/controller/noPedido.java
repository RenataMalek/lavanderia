package controller;

public class noPedido {

	private noPedido proximo;
	private noPedido anterior;
	private Object idPedido;
	private Object idCliente;
	private Object tipoPedido;
	private Object peso;
	private Object valorTotal;
	private Object dataColeta;
	private Object dataDevolucao;

	public noPedido(noPedido proximo, Object idPedido, Object idCliente, Object tipoPedido, Object peso,
			Object valorTotal, Object dataColeta, Object dataDevolucao) {
		this.proximo = proximo;
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.tipoPedido = tipoPedido;
		this.peso = peso;
		this.valorTotal = valorTotal;
		this.dataColeta = dataColeta;
		this.dataDevolucao = dataDevolucao;
	}

	public noPedido(Object idPedido, Object idCliente, Object tipoPedido, Object peso, Object valorTotal,
			Object dataColeta, Object dataDevolucao) {
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.tipoPedido = tipoPedido;
		this.peso = peso;
		this.valorTotal = valorTotal;
		this.dataColeta = dataColeta;
		this.dataDevolucao = dataDevolucao;
	}

	public void setProxima(noPedido proximo) {
		this.proximo = proximo;
	}

	public noPedido getProxima() {
		return proximo;
	}

	public void setAnterior(noPedido anterior) {
		this.anterior = anterior;
	}

	public noPedido getAnterior() {
		return anterior;
	}

	public Object getIDPedido() {
		return idPedido;
	}

	public Object getIDCliente() {
		return idCliente;
	}

	public Object getTipoPedido() {
		return tipoPedido;
	}

	public Object getPeso() {
		return peso;
	}

	public Object getValorTotal() {
		return valorTotal;
	}

	public Object getDataColeta() {
		return dataColeta;
	}

	public Object getDataDevolucao() {
		return dataDevolucao;
	}

}
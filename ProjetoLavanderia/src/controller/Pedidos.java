package controller;

import javax.swing.JOptionPane;

import java.io.Serializable;

public class Pedidos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private noPedido primeira;
	private noPedido ultima;
	private int totalDeElementos = 0;

	private boolean verificarPosicao(int posicao) {
		return posicao >= 0 && posicao < this.totalDeElementos;
	}

	private noPedido capturarNO(int posicao) {
		if (!this.verificarPosicao(posicao)) {
			JOptionPane.showMessageDialog(null, "Pedido não existe");
		}

		noPedido atual = primeira;
		for (int i = 0; i < posicao; i++) {
			atual = atual.getProxima();
		}
		return atual;
	}

	public void adicionaPedidos(Object idPedido, Object idCliente, Object tipoPedido, Object peso, Object valorTotal,
			Object dataColeta, Object dataDevolucao, Object status) {
		if (this.totalDeElementos == 0) {
			noPedido nova = new noPedido(this.primeira, idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta,
					dataDevolucao, status);
			this.primeira = nova;
			this.ultima = this.primeira;
		} else {
			noPedido nova = new noPedido(idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao,
					status);
			this.ultima.setProxima(nova);
			this.ultima = nova;
		}
		this.totalDeElementos++;
	}

	public void exibeTodosPedidos() {
		noPedido aux = primeira;
		String mostra = "";

		while (aux != null) {

			mostra += "ID Pedido: " + aux.getIDPedido() + "\nID Cliente: " + aux.getIDCliente() + "\nTipo de pedido: "
					+ aux.getTipoPedido() + "\nPeso: " + aux.getPeso() + "\nValor R$: " + aux.getValorTotal()
					+ "\nData do pedido: " + aux.getDataColeta() + "\nData de devolução: " + aux.getDataDevolucao()
					+ "\nStatus: " + aux.getStatus() + "\n***************\n";
			aux = aux.getProxima();

		}

		JOptionPane.showMessageDialog(null, mostra);
	}

	public void exibeUmPedido(int idPedido) {

		if (!this.verificarPosicao(idPedido)) {
			JOptionPane.showMessageDialog(null, "Pedido não cadastrado");
		} else {

			noPedido mostra = this.capturarNO(idPedido);

			JOptionPane.showMessageDialog(null,
					"ID Pedido: " + mostra.getIDPedido() + "\nID Cliente: " + mostra.getIDCliente()
							+ "\nTipo de pedido: " + mostra.getTipoPedido() + "\nPeso: " + mostra.getPeso()
							+ "\nValor R$: " + mostra.getValorTotal() + "\nData do pedido: " + mostra.getDataColeta()
							+ "\nData de devolução: " + mostra.getDataDevolucao() + "\nStatus: " + mostra.getStatus()
							+ "\n***************\n");
		}

	}

	public void cancelarPedido(int idPedido) {

		if (!this.verificarPosicao(idPedido)) {
			JOptionPane.showMessageDialog(null, "Pedido não cadastrado");
		} else {

			noPedido cancela = this.capturarNO(idPedido);

			cancela.setStatus("Cancelado");
			JOptionPane.showMessageDialog(null, "Pedido cancelado!");
		}

	}
}
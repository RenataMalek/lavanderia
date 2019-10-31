package controller;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class PedidosUrgentes {

	private noPedido primeira;
	private noPedido ultima;
	private int totalDeElementos;

	public void lerPedidos() throws IOException {
//arrumar esse metodo
	}

	private boolean verificarPosicao(int posicao) {
		return posicao >= 0 && posicao < this.totalDeElementos;
	}

	private noPedido capturarNO(int posicao) {
		if (!this.verificarPosicao(posicao)) {
			JOptionPane.showMessageDialog(null, "Pedido não existe");
		} else {
			noPedido atual = primeira;
			int aux = 0;
			localizar(aux, atual, posicao);
			return atual;
		}
		return null;

	}

	private noPedido localizar(int aux, noPedido atual, int posicao) {

		if (aux == posicao) {
			return atual;
		} else {
			atual = atual.getProxima();
			aux++;
			return localizar(aux, atual, posicao);
		}

	}

	public void adicionaUrgente(Object idPedido, Object idCliente, Object tipoPedido, Object peso, Object valorTotal,
			Object dataColeta, Object dataDevolucao) {
		if (this.totalDeElementos == 0) {
			noPedido nova = new noPedido(idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao);
			this.primeira = nova;
			this.ultima = nova;
		} else {
			noPedido nova = new noPedido(this.primeira, idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta,
					dataDevolucao);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}
		this.totalDeElementos++;
	}

	//criar metodo que exibe de ua=ma só vez todos os pedidos normais e urgentes em ordem
	public void exibeTodosPedidos() {
		noPedido aux = primeira;
		String mostra = "";
		String aux1;
		int enviaIDCliente;
		Clientes cliente = new Clientes();
		while (aux != null) {
			aux1 = (String) aux.getIDCliente();
			enviaIDCliente = Integer.parseInt(aux1);

			cliente.exibeUmCliente(enviaIDCliente);

			mostra += "Tipo de pedido: " + aux.getTipoPedido() + "\nPeso: " + aux.getPeso() + "\nValor : "
					+ aux.getValorTotal() + "\nData do pedido: " + aux.getDataColeta() + "\nData de devolução: "
					+ aux.getDataDevolucao() + "\n";
			aux = aux.getProxima();
		}

		JOptionPane.showMessageDialog(null, mostra);
	}

	public void exibeUmPedido(int idPedido) {
		String aux1;
		int enviaIDCliente;
		Clientes cliente = new Clientes();

		if (!this.verificarPosicao(idPedido)) {
			JOptionPane.showMessageDialog(null, "Pedido não cadastrado");
		} else {

			noPedido mostra = this.capturarNO(idPedido);
			aux1 = (String) mostra.getIDCliente();
			enviaIDCliente = Integer.parseInt(aux1);
			cliente.exibeUmCliente(enviaIDCliente);

			JOptionPane.showMessageDialog(null,
					"Tipo de pedido: " + mostra.getTipoPedido() + "\nPeso: " + mostra.getPeso() + "\nValor : "
							+ mostra.getValorTotal() + "\nData do pedido: " + mostra.getDataColeta()
							+ "\nData de devolução: " + mostra.getDataDevolucao());
		}
	}

	public void gravarPedidosUrgentes() throws IOException {
//arrumar esse metodo
	}

}

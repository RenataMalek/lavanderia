package controller;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pedidos {

	private noPedido primeira;
	private noPedido ultima;
	private int totalDeElementos;

	public void lerPedidos() throws IOException {

		Object idPedido, idCliente, tipo, peso, valorTotal, dataColeta, dataDevolucao;
		FileReader fileName = new FileReader("arquivoPedidos.txt");

		BufferedReader ler = new BufferedReader(fileName);

		while (ler.readLine() != null) {
			idPedido = ler.readLine();
			idCliente = ler.readLine();
			tipo = ler.readLine();
			peso = ler.readLine();
			valorTotal = ler.readLine();
			dataColeta = ler.readLine();
			dataDevolucao = ler.readLine();

			if (this.totalDeElementos == 0) {
				noPedido nova = new noPedido(idPedido, idCliente, tipo, peso, valorTotal, dataColeta, dataDevolucao);
				this.primeira = nova;
				this.ultima = nova;
			} else {
				noPedido nova = new noPedido(this.primeira, idPedido, idCliente, tipo, peso, valorTotal, dataColeta,
						dataDevolucao);
				this.primeira.setAnterior(nova);
				this.primeira = nova;
			}
			this.totalDeElementos++;

		}

		ler.close();
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

	public void adiciona(Object idPedido, Object idCliente, Object tipoPedido, Object peso, Object valorTotal,
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

	public void adicionaPrioridade(Object idPedido, Object idCliente, Object tipoPedido, Object peso, Object valorTotal,
			Object dataColeta, Object dataDevolucao) {
		// adicionar com prioridade
	}

	public void removeInicio() {
		if (!this.verificarPosicao(0)) {
			JOptionPane.showMessageDialog(null, "Pedido não existe");
		}

		this.primeira = this.primeira.getProxima();
		this.totalDeElementos--;

		if (this.totalDeElementos == 0) {
			this.ultima = null;
		}
	}

	public void remove(int posicao) {
		if (!this.verificarPosicao(posicao)) {
			JOptionPane.showMessageDialog(null, "Pedido não existe");
		}

		if (posicao == 0) {
			this.removeInicio();
		} else if (posicao == this.totalDeElementos - 1) {
			this.removeFim();
		} else {
			noPedido anterior = this.capturarNO(posicao - 1);
			noPedido atual = anterior.getProxima();
			noPedido proxima = atual.getProxima();

			anterior.setProxima(proxima);
			proxima.setAnterior(anterior);

			this.totalDeElementos--;
		}
	}

	public void removeFim() {
		if (!this.verificarPosicao(this.totalDeElementos - 1)) {
			JOptionPane.showMessageDialog(null, "Posição não existe");
		}
		if (this.totalDeElementos == 1) {
			this.removeInicio();
		} else {
			noPedido penultima = this.ultima.getAnterior();
			penultima.setProxima(null);
			this.ultima = penultima;
			this.totalDeElementos--;
		}
	}

	public void removeAtrelados(int idCliente) {
		noPedido aux = primeira;
		int posicao;
		while (aux != null) {
			if (idCliente == (int) aux.getIDCliente()) {
				posicao = (int) aux.getIDPedido();
				remove(posicao);
			}
			aux = aux.getProxima();
		}
	}

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

	public void gravarPedidos() throws IOException {

		FileWriter txt = new FileWriter("c:\\arquivoPedidos.txt");
		PrintWriter gravarTxt = new PrintWriter(txt);

		noPedido aux = primeira;

		while (aux != null) {
			gravarTxt.printf(aux.getIDPedido() + "\n");
			gravarTxt.printf(aux.getIDCliente() + "\n");
			gravarTxt.printf(aux.getTipoPedido() + "\n");
			gravarTxt.printf(aux.getPeso() + "\n");
			gravarTxt.printf(aux.getValorTotal() + "\n");
			gravarTxt.printf(aux.getDataColeta() + "\n");
			gravarTxt.printf(aux.getDataDevolucao() + "\n");
			aux = aux.getProxima();
		}

		txt.close();
	}

}

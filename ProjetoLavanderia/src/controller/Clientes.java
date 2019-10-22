package controller;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Clientes {
	private noCliente primeira;
	private noCliente ultima;
	private int totalDeElementos;

	public void lerClientes() throws IOException {

		Object idCliente, nome, CPF, telefone, email;
		FileReader fileName = new FileReader("arquivoClientes.txt");

		BufferedReader ler = new BufferedReader(fileName);

		while (ler.readLine() != null) {

			idCliente = ler.readLine();
			nome = ler.readLine();
			CPF = ler.readLine();
			telefone = ler.readLine();
			email = ler.readLine();

			if (this.totalDeElementos == 0) {
				noCliente nova = new noCliente(idCliente, nome, CPF, telefone, email);
				this.primeira = nova;
				this.ultima = nova;
			} else {
				noCliente nova = new noCliente(this.primeira, idCliente, nome, CPF, telefone, email);
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

	private noCliente capturarNO(int posicao) {
		if (!this.verificarPosicao(posicao)) {
			JOptionPane.showMessageDialog(null, "Cliente não existe");
		} else {
			noCliente atual = primeira;
			int aux = 0;
			localizar(aux, atual, posicao);
			return atual;
		}
		return null;
	}

	private noCliente localizar(int aux, noCliente atual, int posicao) {

		if (aux == posicao) {
			return atual;
		} else {
			atual = atual.getProxima();
			aux++;
			return localizar(aux, atual, posicao);
		}

	}

	public void adiciona(Object idCliente, Object nomeCliente, Object CPF, Object telefone, Object email) {
		if (this.totalDeElementos == 0) {
			noCliente nova = new noCliente(idCliente, nomeCliente, CPF, telefone, email);
			this.primeira = nova;
			this.ultima = nova;
		} else {
			noCliente nova = new noCliente(this.primeira, idCliente, nomeCliente, CPF, telefone, email);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}
		this.totalDeElementos++;
	}

	public void removeInicio() {
		if (!this.verificarPosicao(0)) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
		}

		this.primeira = this.primeira.getProxima();
		this.totalDeElementos--;

		if (this.totalDeElementos == 0) {
			this.ultima = null;
		}
	}

	public void remove(int posicao) {
		if (!this.verificarPosicao(posicao)) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
		} else {

			if (posicao == 0) {
				this.removeInicio();
			} else if (posicao == this.totalDeElementos - 1) {
				this.removeFim();
			} else {
				noCliente anterior = this.capturarNO(posicao - 1);
				noCliente atual = anterior.getProxima();
				noCliente proxima = atual.getProxima();

				anterior.setProxima(proxima);
				proxima.setAnterior(anterior);

				this.totalDeElementos--;
			}
		}
	}

	public void removeFim() {
		if (!this.verificarPosicao(this.totalDeElementos - 1)) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
		}
		if (this.totalDeElementos == 1) {
			this.removeInicio();
		} else {
			noCliente penultima = this.ultima.getAnterior();
			penultima.setProxima(null);
			this.ultima = penultima;
			this.totalDeElementos--;
		}
	}

	public void exibeTodosCliente() {
		noCliente aux = primeira;
		String mostra = "";
		while (aux != null) {
			mostra += "Nome: " + aux.getNomeCliente() + "\nCPF: " + aux.getCPF() + "\nTelefone " + aux.getTelefone()
					+ "\nE-mail: " + aux.getEmail() + "\n";
			aux = aux.getProxima();
		}

		JOptionPane.showMessageDialog(null, mostra);
	}

	public void exibeUmCliente(int idCliente) {
		if (!this.verificarPosicao(idCliente)) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
		}

		noCliente mostra = this.capturarNO(idCliente);

		JOptionPane.showMessageDialog(null, "Nome: " + mostra.getNomeCliente() + "\nCPF: " + mostra.getCPF()
				+ "\nTelefone : " + mostra.getTelefone() + "\nE-mail: " + mostra.getEmail());
	}

	public void gravarClientes() throws IOException {

		FileWriter txt = new FileWriter("c:\\arquivoClientes.txt");
		PrintWriter gravarTxt = new PrintWriter(txt);

		noCliente aux = primeira;

		while (aux != null) {
			gravarTxt.printf(aux.getIDCliente() + "\n");
			gravarTxt.printf(aux.getNomeCliente() + "\n");
			gravarTxt.printf(aux.getCPF() + "\n");
			gravarTxt.printf(aux.getTelefone() + "\n");
			gravarTxt.printf(aux.getEmail() + "\n");
			aux = aux.getProxima();
		}

		txt.close();
	}
}

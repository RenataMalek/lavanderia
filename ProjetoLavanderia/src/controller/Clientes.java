package controller;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class Clientes {
	private noCliente primeira;
	private noCliente ultima;
	private int totalDeElementos;

	public void lerClientes() throws IOException {
//arrumar todo esse metodo
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

	@SuppressWarnings("resource")
	public void gravarClientes() throws IOException {
//arrumar todo esse metodo
		FileOutputStream gravar = new FileOutputStream("arquivoCliente");
		ObjectOutputStream cliente = new ObjectOutputStream(gravar);

		cliente.writeObject(getClass());

	}

}

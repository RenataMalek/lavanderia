package controller;

import javax.swing.JOptionPane;

import java.io.Serializable;

public class Clientes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private noCliente primeira;
	private noCliente ultima;
	private int totalDeElementos = 0;

	public boolean verificarPosicao(int posicao) {
		return posicao >= 0 && posicao < this.totalDeElementos;
	}

	private noCliente capturarNO(int posicao) {
		if (!this.verificarPosicao(posicao)) {
			JOptionPane.showMessageDialog(null, "Cliente n�o cadastrado");
		}

		noCliente atual = primeira;
		for (int i = 0; i < posicao; i++) {
			atual = atual.getProxima();
		}
		return atual;
	}

	public void adiciona(Object idCliente, Object nomeCliente, Object CPF, Object telefone, Object email) {
		if (this.totalDeElementos == 0) {
			noCliente nova = new noCliente(this.primeira, idCliente, nomeCliente, CPF, telefone, email);
			this.primeira = nova;
			this.ultima = this.primeira;
		} else {
			noCliente nova = new noCliente(idCliente, nomeCliente, CPF, telefone, email);
			this.ultima.setProxima(nova);
			this.ultima = nova;
		}
		this.totalDeElementos++;
	}

	public void exibeTodosCliente() {
		noCliente aux = primeira;
		String mostra = "";
		while (aux != null) {
			mostra += "ID: " + aux.getIDCliente() + "\nNome: " + aux.getNomeCliente() + "\nCPF: " + aux.getCPF()
					+ "\nTelefone: " + aux.getTelefone() + "\nE-mail: " + aux.getEmail() + "\n***************\n";

			aux = aux.getProxima();
		}

		JOptionPane.showMessageDialog(null, mostra);
	}

	public void exibeUmCliente(int idCliente) {

		noCliente mostra = this.capturarNO(idCliente);

		JOptionPane.showMessageDialog(null,
				"ID: " + mostra.getIDCliente() + "\nNome: " + mostra.getNomeCliente() + "\nCPF: " + mostra.getCPF()
						+ "\nTelefone: " + mostra.getTelefone() + "\nE-mail: " + mostra.getEmail()
						+ "\n***************\n");

	}

	public void editaCliente(int idCliente, Object nomeCliente, Object CPF, Object telefone, Object email) {

		if (!this.verificarPosicao(idCliente)) {
			JOptionPane.showMessageDialog(null, "Pedido n�o cadastrado");
		} else {

			noCliente atualiza = this.capturarNO(idCliente);

			atualiza.setNomeCliente(nomeCliente);
			atualiza.setCPF(CPF);
			atualiza.setTelefone(telefone);
			atualiza.setEmail(email);
			JOptionPane.showMessageDialog(null, "Cliente Atualizado!");
		}

	}

}
package controller;

import java.io.Serializable;

public class noCliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private noCliente proximo;
	private noCliente anterior;
	private Object idCliente;
	private Object nomeCliente;
	private Object CPF;
	private Object telefone;
	private Object email;

	public noCliente(noCliente proximo, Object idCliente, Object nomeCliente, Object CPF, Object telefone,
			Object email) {
		this.proximo = proximo;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.CPF = CPF;
		this.telefone = telefone;
		this.email = email;
	}

	public noCliente(Object idCliente, Object nomeCliente, Object CPF, Object telefone, Object email) {
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.CPF = CPF;
		this.telefone = telefone;
		this.email = email;
	}

	public void setProxima(noCliente proximo) {
		this.proximo = proximo;
	}

	public noCliente getProxima() {
		return proximo;
	}

	public void setAnterior(noCliente anterior) {
		this.anterior = anterior;
	}

	public noCliente getAnterior() {
		return anterior;
	}

	public Object getIDCliente() {
		return idCliente;
	}

	public void setNomeCliente(Object nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Object getNomeCliente() {
		return nomeCliente;
	}

	public void setCPF(Object CPF) {
		this.CPF = CPF;
	}

	public Object getCPF() {
		return CPF;
	}

	public void setTelefone(Object telefone) {
		this.telefone = telefone;
	}

	public Object getTelefone() {
		return telefone;
	}

	public void setEmail(Object email) {
		this.email = email;
	}

	public Object getEmail() {
		return email;
	}

}

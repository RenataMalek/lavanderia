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
		String mostra = "";
		String tipoOrdem="s";
		noPedido[] vetorPedidos = new noPedido[totalDeElementos];
		
		//--------CHAMADA DO QUICKSORT------------	
		quickSort(carregarVetor(vetorPedidos),0,(carregarVetor(vetorPedidos).length)-1,tipoOrdem);
		//--------CHAMADA DO QUICKSORT------------
		
		ordenaPedido(vetorPedidos,0,vetorPedidos.length-1);
		
		for(int i=0;i<vetorPedidos.length;i++) {
			mostra += "ID Pedido: " + vetorPedidos[i].getIDPedido() + "\nID Cliente: " + vetorPedidos[i].getIDCliente() + "\nTipo de pedido: "
					+ vetorPedidos[i].getTipoPedido() + "\nPeso: " + vetorPedidos[i].getPeso() + "\nValor R$: " + vetorPedidos[i].getValorTotal()
					+ "\nData do pedido: " + vetorPedidos[i].getDataColeta() + "\nData de devolução: " + vetorPedidos[i].getDataDevolucao()
					+ "\nStatus: " + vetorPedidos[i].getStatus() + "\n***************\n";
			
		}
				
		JOptionPane.showMessageDialog(null, mostra);
	}
	
	public static void ordenaPedido (noPedido vet[], int ini, int fim)
	{
		int divisao; 
		String tipo="p";
		if (ini < fim) { 
			divisao = quickSort(vet, ini, fim,tipo); // separa a partir do primeiro
			ordenaPedido (vet, ini, divisao-1); // ordena a primeira parte 
			ordenaPedido (vet, divisao+1, fim); // ordena a segunda parte
		}
	}
	
	public noPedido[] carregarVetor(noPedido[] vetorQuick) {
		noPedido aux = primeira;
		int cont = 0;
		
		while(aux!= null) {
			vetorQuick[cont] = aux;
			aux = aux.getProxima();
			cont++;
		}
		return vetorQuick;
	}
	
	//------------------------------------------QUICKSORT--------------------------------------------------------------------------
	public static int quickSort (noPedido[] pedidosSeparar, int primeiro, int ultimo,String tipo)
	{	
		int pivo,p = primeiro+1, u = ultimo, verifAtivoInicio,verifAtivoFinal;
		noPedido aux, inicioVetor=pedidosSeparar[primeiro];
		
		if(tipo=="s") {
			pivo=verificaStatus(pedidosSeparar,primeiro,tipo);
		}
		else {
			pivo=1;
		}
		
		while (p<=u) {
			verifAtivoInicio = verificaStatus(pedidosSeparar,p,tipo);
			verifAtivoFinal = verificaStatus(pedidosSeparar,u,tipo);
			
			while (p <= ultimo && verifAtivoInicio <= pivo) { 
				++p;
				if(p<=pedidosSeparar.length-1) {
					verifAtivoInicio = verificaStatus(pedidosSeparar,p,tipo);
				}
			}
			while (pivo < verifAtivoFinal) {
				--u;
				verifAtivoFinal = verificaStatus(pedidosSeparar,u,tipo);
			}
			if (p < u){ 
				aux = pedidosSeparar[p];
				pedidosSeparar[p] = pedidosSeparar[u];
				pedidosSeparar[u] = aux; 
				++p; 
				--u;
			}	
		} 
		if (primeiro != u){
			pedidosSeparar[primeiro] = pedidosSeparar[u];
			pedidosSeparar[u] = inicioVetor;
		} 
		return u; 
	}
	//------------------------------------------QUICKSORT--------------------------------------------------------------------------
	
	public static int verificaStatus(noPedido[] vetorOrdenar, int posicaoVetor,String tipoVerif){
		int statusPedidoNumerico,numeroPedido;
		String statusPedido;
		statusPedido = (vetorOrdenar[posicaoVetor].getStatus()).toString();
		numeroPedido = Integer.parseInt((vetorOrdenar[posicaoVetor].getIDPedido()).toString());
		
		if(tipoVerif=="s") {
			if(statusPedido.length()==9) {
				statusPedidoNumerico=1;
			}
			else {
				statusPedidoNumerico=0;
			}
		}
		else {
			statusPedidoNumerico = numeroPedido;
		}
		
		return statusPedidoNumerico;	
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

	public int verificaIDPedido() {

		int cont = 0;
		noPedido verifica = ultima;

		cont = (int) verifica.getIDPedido();
		cont++;
		return cont;

	}
}
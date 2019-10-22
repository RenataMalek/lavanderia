
package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import controller.Clientes;
import controller.Pedidos;
import controller.Metodos;

public class Principal {

	public static void main(String[] args) throws IOException, ParseException {

		Clientes cliente = new Clientes();
		int idCliente = 0, busca;
		String nomeCliente, telefone, CPF, email;

		Pedidos pedido = new Pedidos();
		int idPedido = 0, tipoPedido, peso, valor, valorTotal;
		Date data;
		String recebeData;
		String dataColeta;
		String dataDevolucao;

		Metodos metodos = new Metodos();

		cliente.lerClientes();
		pedido.lerPedidos();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		// tela com botao de novo cliente, nodo pedido, exibir todos os clientes, exibir
		// todos os pedidos, exibir um cliente, exibir um pedido, excluir um cliente,
		// excluir um pedido

		// bot�o para adicionar novo cliente

		nomeCliente = JOptionPane.showInputDialog("Label nome");
		CPF = JOptionPane.showInputDialog("Label CPF");
		while (Metodos.verificarCPF(CPF) == false) {

			JOptionPane.showMessageDialog(null, "CPF Invalido, insira novamente");
			CPF = JOptionPane.showInputDialog("Label CPF");
		}

		telefone = JOptionPane.showInputDialog("Label telefone");
		email = JOptionPane.showInputDialog("Label email");

		cliente.adiciona(idCliente, nomeCliente, CPF, telefone, email);
		idCliente++;

		// bot�o para mostrar clientes
		cliente.exibeTodosCliente();

		// bot�o para mostrar um cliente espec�fico
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para Infomar o ID do cliente a ser pesquisado"));
		// se der tempo mudar pesquisa para buscar o CPF do cliente
		cliente.exibeUmCliente(busca);
		// se der tempo, mostrar os pedidos atrelados ao cliente especifico

		// botao para excluir cliente
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para informar o ID do cliente que ser� excluido"));
		cliente.remove(busca);
		pedido.removeAtrelados(busca); // testar para remover todos os pedidos com mesmo idCliente atrelado

		// botao adicionar pedidos - enviar o ID do respectivo cliente

		idCliente = Integer.parseInt(JOptionPane.showInputDialog("Label para digitar ID do cliente"));

		tipoPedido = Integer.parseInt(JOptionPane.showInputDialog(
				"retornar se � normal ou urgente, por m�todo - 0 para normal e 1 para urgente - um combobox para escoher, o clicado retorna 0 ou 1"));

		peso = Integer.parseInt(JOptionPane.showInputDialog("Label Peso"));

		valor = Integer.parseInt(JOptionPane.showInputDialog("recebe valor para cada 1k de pe�as"));

		valorTotal = valor * peso;

		recebeData = JOptionPane.showInputDialog("label que recebe a data que est� deixando as pe�as na lavanderia");

		data = formato.parse(recebeData);
		dataColeta = formato.format(data);

		// dataDevolucao = metodos.verificarDataDevolucao(tipoPedido, dataColeta);

		recebeData = JOptionPane.showInputDialog("label que recebe a data que dever� retirar as pe�as na lavanderia");

		data = formato.parse(recebeData);
		dataDevolucao = formato.format(data);

		if (tipoPedido == 0) {
			pedido.adiciona(idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao);
			idPedido++;

		} else if (tipoPedido == 1) {
			pedido.adicionaPrioridade(idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao);
			idPedido++;
		}

		// botao que todos mostrar pedidos - mostra os dados do cliente
		pedido.exibeTodosPedidos();

		// botao motrar pedido espec�fico - mostra os dados do cliente
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para Infomar o ID do pedido a ser pesquisado"));
		pedido.exibeUmPedido(busca);

		// botao excluir pedido
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para informar o ID do pedido que ser� excluido"));
		pedido.remove(busca);

		cliente.gravarClientes();
		pedido.gravarPedidos();

	}

}

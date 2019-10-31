
package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import controller.Clientes;
import controller.PedidosNormais;
import controller.PedidosUrgentes;
import controller.Metodos;

public class Principal {

	public static void main(String[] args) throws IOException, ParseException {

		Clientes cliente = new Clientes();
		PedidosNormais pedidoN = new PedidosNormais();
		PedidosUrgentes pedidoU = new PedidosUrgentes();
		Metodos metodos = new Metodos();

		int idCliente = 0, busca;
		String nomeCliente, telefone, CPF, email;

		int idPedido = 0, peso, valor, valorTotal;
		Date data;
		String recebeData, dataColeta, dataDevolucao, tipoPedido;

		cliente.lerClientes();
		pedidoN.lerPedidos();
		// pedidoU.lerPedidos();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		// tela com botao de novo cliente, nodo pedido, exibir todos os clientes, exibir
		// todos os pedidos, exibir um cliente, exibir um pedido, excluir um cliente,
		// excluir um pedido

		// botão para adicionar novo cliente

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

		// botão para mostrar clientes
		cliente.exibeTodosCliente();

		// botão para mostrar um cliente específico
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para Infomar o ID do cliente a ser pesquisado"));
		// se der tempo mudar pesquisa para buscar o CPF do cliente
		cliente.exibeUmCliente(busca);
		// se der tempo, mostrar os pedidos atrelados ao cliente especifico

		// botao adicionar pedidos - enviar o ID do respectivo cliente

		idCliente = Integer.parseInt(JOptionPane.showInputDialog("Label para digitar ID do cliente"));

		tipoPedido = JOptionPane.showInputDialog(
				"retornar se é normal ou urgente, por método - 0 para normal e 1 para urgente - um combobox para escoher, o clicado retorna 0 ou 1");

		peso = Integer.parseInt(JOptionPane.showInputDialog("Label Peso"));

		valor = Integer.parseInt(JOptionPane.showInputDialog("recebe valor para cada 1k de peças"));

		valorTotal = valor * peso;

		recebeData = JOptionPane.showInputDialog("label que recebe a data que está deixando as peças na lavanderia");

		data = formato.parse(recebeData);
		dataColeta = formato.format(data);

		// dataDevolucao = metodos.verificarDataDevolucao(tipoPedido, dataColeta);

		recebeData = JOptionPane.showInputDialog("label que recebe a data que deverá retirar as peças na lavanderia");

		data = formato.parse(recebeData);
		dataDevolucao = formato.format(data);

		if (tipoPedido == "") {
			pedidoN.adicionaNormal(idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao);
			idPedido++;

			// } else if (tipoPedido == ) {
			// pedido.adicionaPrioridade(idPedido, idCliente, tipoPedido, peso, valorTotal,
			// dataColeta, dataDevolucao);
			// idPedido++;
		}

		// botao que todos mostrar pedidos - mostra os dados do cliente
		pedidoN.exibeTodosPedidos();

		// botao motrar pedido específico - mostra os dados do cliente
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para Infomar o ID do pedido a ser pesquisado"));
		pedidoN.exibeUmPedido(busca);

		// botao cancelar pedido
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para informar o ID do pedido que será excluido"));
		// pedido.remove(busca);

		cliente.gravarClientes();
		pedidoN.gravarPedidosNormais();

	}

}

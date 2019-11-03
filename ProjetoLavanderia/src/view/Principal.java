
package view;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import controller.Clientes;
import controller.Pedidos;
import controller.Metodos;

public class Principal {
	static Clientes cliente = new Clientes();
	static Pedidos pedido = new Pedidos();

	public static void main(String[] args) throws IOException, ParseException {

		Metodos metodos = new Metodos();

		int idCliente = 0, busca;
		String nomeCliente, telefone, CPF, email;

		int idPedido = 0, peso, valor, valorTotal;
		Date data;
		String recebeData, dataColeta, dataDevolucao, tipoPedido, status;
		try {

			ObjectInputStream lerCliente = new ObjectInputStream(new FileInputStream("clientes.txt"));
			cliente = (Clientes) lerCliente.readObject();

			ObjectInputStream lerPedido = new ObjectInputStream(new FileInputStream("pedidos.txt"));
			pedido = (Pedidos) lerPedido.readObject();

			lerCliente.close();
			lerPedido.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "não foi possível concluir a leitura de arquivos");
		}

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
		cliente.exibeUmCliente(busca);

		// botao adicionar pedidos - enviar o ID do respectivo cliente

		idCliente = Integer.parseInt(JOptionPane.showInputDialog("Label para digitar ID do cliente"));

		if (cliente.verificarPosicao(idCliente) == false) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado, favor cadastrar e iniciar o pedido novamente");
		} else {

			tipoPedido = JOptionPane.showInputDialog(
					"retornar se é normal ou urgente, por método - 0 para normal e 1 para urgente - um combobox para escoher, o clicado retorna 0 ou 1");

			peso = Integer.parseInt(JOptionPane.showInputDialog("Label Peso"));

			valor = Integer.parseInt(JOptionPane.showInputDialog("recebe valor para cada 1k de peças"));

			valorTotal = valor * peso;

			recebeData = JOptionPane
					.showInputDialog("label que recebe a data que está deixando as peças na lavanderia");

			data = formato.parse(recebeData);
			dataColeta = formato.format(data);

			// dataDevolucao = metodos.verificarDataDevolucao(tipoPedido, dataColeta);

			recebeData = JOptionPane
					.showInputDialog("label que recebe a data que deverá retirar as peças na lavanderia");

			data = formato.parse(recebeData);
			dataDevolucao = formato.format(data);

			String status1 = "";

			pedido.adicionaPedidos(idPedido, idCliente, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao,
					status1);
			idPedido++;

		}

		// botao que todos mostrar pedidos - mostra os dados do cliente
		pedido.exibeTodosPedidos();

		// botao motrar pedido específico - mostra os dados do cliente
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para Infomar o ID do pedido a ser pesquisado"));
		pedido.exibeUmPedido(busca);

		// botao cancelar pedido
		busca = Integer.parseInt(JOptionPane.showInputDialog("Label para informar o ID do pedido que será excluido"));
		// pedido.remove(busca);

		try {
			ObjectOutputStream gravarCliente = new ObjectOutputStream(new FileOutputStream("clientes.txt"));
			gravarCliente.writeObject(cliente);

			ObjectOutputStream gravarPedido = new ObjectOutputStream(new FileOutputStream("pedidos.txt"));
			gravarPedido.writeObject(pedido);

			gravarCliente.close();
			gravarPedido.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "não foi possível concluir a gravação de arquivos");
		}

	}

}

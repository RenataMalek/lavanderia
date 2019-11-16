package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CarregarDados;
import controller.Clientes;
import controller.GravarDados;
import controller.validarCPF;
import controller.Pedidos;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tela extends JFrame implements CarregarDados, GravarDados {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	static Clientes cliente = new Clientes();
	static Pedidos pedido = new Pedidos();
	validarCPF metodos = new validarCPF();

	private static int idCliente = 0;
	private static int idPedido = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Admin\\Desktop\\FATEC\\3\u00BA SEMESTRE\\Estrutura de Dados\\docs e informa\u00E7ao do projeto\\bolhas.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 383);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAdicionarNovoCliente = new JButton("Adicionar novo cliente");
		btnAdicionarNovoCliente.setForeground(Color.BLACK);
		btnAdicionarNovoCliente.setBackground(Color.CYAN);
		btnAdicionarNovoCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdicionarNovoCliente.setBounds(10, 84, 167, 36);
		contentPane.add(btnAdicionarNovoCliente);

		JButton btnAdicionarNovoPedido = new JButton("Adicionar novo pedido");
		btnAdicionarNovoPedido.setForeground(Color.BLACK);
		btnAdicionarNovoPedido.setBackground(Color.CYAN);
		btnAdicionarNovoPedido.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdicionarNovoPedido.setBounds(191, 84, 167, 36);
		contentPane.add(btnAdicionarNovoPedido);

		JButton btnLocalizarCliente = new JButton("Localizar cliente");
		btnLocalizarCliente.setBackground(Color.CYAN);
		btnLocalizarCliente.setForeground(Color.BLACK);
		btnLocalizarCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLocalizarCliente.setBounds(10, 131, 167, 36);
		contentPane.add(btnLocalizarCliente);

		JButton btnExibirClientes = new JButton("Exibir clientes");
		btnExibirClientes.setForeground(Color.BLACK);
		btnExibirClientes.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExibirClientes.setBackground(Color.CYAN);
		btnExibirClientes.setBounds(10, 182, 167, 36);
		contentPane.add(btnExibirClientes);

		JButton btnLocalizarPedido = new JButton("Localizar pedido");
		btnLocalizarPedido.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLocalizarPedido.setBackground(Color.CYAN);
		btnLocalizarPedido.setBounds(191, 131, 167, 36);
		contentPane.add(btnLocalizarPedido);

		JButton btnCancelarPedido = new JButton("Cancelar pedido");
		btnCancelarPedido.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelarPedido.setBackground(Color.CYAN);
		btnCancelarPedido.setBounds(191, 228, 167, 36);
		contentPane.add(btnCancelarPedido);

		JButton btnExibirPedidos = new JButton("Exibir pedidos");
		btnExibirPedidos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExibirPedidos.setBackground(Color.CYAN);
		btnExibirPedidos.setBounds(191, 182, 167, 36);
		contentPane.add(btnExibirPedidos);

		JButton btnAtualizarCliente = new JButton("Editar Cliente");
		btnAtualizarCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAtualizarCliente.setBackground(Color.CYAN);
		btnAtualizarCliente.setBounds(10, 229, 167, 36);
		contentPane.add(btnAtualizarCliente);

		JButton btnIniciarLeitura = new JButton("Carregar dados");
		btnIniciarLeitura.setBounds(10, 280, 167, 23);
		contentPane.add(btnIniciarLeitura);

		JButton btnIniciarGravacao = new JButton("Gravar dados");
		btnIniciarGravacao.setBounds(191, 280, 167, 23);
		contentPane.add(btnIniciarGravacao);

		JLabel lblCleanLavanderia = new JLabel("Clean Lavanderia");
		lblCleanLavanderia.setForeground(Color.BLUE);
		lblCleanLavanderia.setFont(new Font("Constantia", Font.BOLD, 20));
		lblCleanLavanderia.setBounds(103, 29, 167, 41);
		contentPane.add(lblCleanLavanderia);

		ActionListener leitor = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lerArquivo();
			}
		};

		ActionListener gravador = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gravarArquivo();
			}
		};

		ActionListener novoCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cadastraCliente();
			}
		};

		ActionListener localizaCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buscarCliente();
			}
		};

		ActionListener exibeTodosClientes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exibirTodosClientes();
			}
		};

		ActionListener atualizarCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualizarCliente();
			}
		};

		ActionListener novoPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					novoPedido();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		};

		ActionListener localizaPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buscarPedido();
			}
		};

		ActionListener exibeTodosPedidos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exibirTodosPedidos();
			}
		};

		ActionListener cancelarPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cancelarPedido();
			}
		};

		btnIniciarLeitura.addActionListener(leitor);
		btnIniciarGravacao.addActionListener(gravador);
		btnAdicionarNovoCliente.addActionListener(novoCliente);
		btnLocalizarCliente.addActionListener(localizaCliente);
		btnExibirClientes.addActionListener(exibeTodosClientes);
		btnAtualizarCliente.addActionListener(atualizarCliente);
		btnAdicionarNovoPedido.addActionListener(novoPedido);
		btnLocalizarPedido.addActionListener(localizaPedido);
		btnExibirPedidos.addActionListener(exibeTodosPedidos);
		btnCancelarPedido.addActionListener(cancelarPedido);

	}

	public void lerArquivo() {

		try {

			ObjectInputStream lerCliente = new ObjectInputStream(new FileInputStream("clientes.txt"));
			cliente = (Clientes) lerCliente.readObject();

			ObjectInputStream lerPedido = new ObjectInputStream(new FileInputStream("pedidos.txt"));
			pedido = (Pedidos) lerPedido.readObject();

			lerCliente.close();
			lerPedido.close();

			JOptionPane.showMessageDialog(null, "Leitura realizada com sucesso!");

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "não foi possível concluir a leitura de arquivos");
		}

		idCliente = cliente.verificaIDCliente();
		idPedido = pedido.verificaIDPedido();

	}

	public void gravarArquivo() {

		try {
			ObjectOutputStream gravarCliente = new ObjectOutputStream(new FileOutputStream("clientes.txt"));
			gravarCliente.writeObject(cliente);

			ObjectOutputStream gravarPedido = new ObjectOutputStream(new FileOutputStream("pedidos.txt"));
			gravarPedido.writeObject(pedido);

			gravarCliente.close();
			gravarPedido.close();

			JOptionPane.showMessageDialog(null, "Gravação finalizada com sucesso!");

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "não foi possível concluir a gravação de arquivos");
		}

	}

	public void cadastraCliente() {

		String nomeCliente, telefone, CPF, email;

		nomeCliente = JOptionPane.showInputDialog("Nome: ");
		CPF = JOptionPane.showInputDialog("CPF");
		
			while (validarCPF.verificarCPF(CPF) == false) {

				JOptionPane.showMessageDialog(null, "CPF Invalido, insira novamente");
				CPF = JOptionPane.showInputDialog("CPF");
			}
		

		telefone = JOptionPane.showInputDialog("Telefone");
		email = JOptionPane.showInputDialog("E-mail");

		JOptionPane.showMessageDialog(null, "Cliente cadastrado! \nSeu ID é " + idCliente + ", guarde esse numero!!");

		cliente.adiciona(idCliente, nomeCliente, CPF, telefone, email);
		idCliente++;

	}

	public void buscarCliente() {

		int busca = Integer.parseInt(JOptionPane.showInputDialog("Infomar o ID do cliente a ser pesquisado"));

		if (busca > idCliente) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
		} else {
			cliente.exibeUmCliente(busca);
		}
	}

	public void exibirTodosClientes() {

		cliente.exibeTodosCliente();
	}

	public void atualizarCliente() {

		String nomeCliente, telefone, CPF, email;

		int recebeID = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cliente"));

		if (cliente.verificarPosicao(recebeID) == false) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
		} else {
			nomeCliente = JOptionPane.showInputDialog("Nome");
			CPF = JOptionPane.showInputDialog("CPF");
			while (validarCPF.verificarCPF(CPF) == false) {

				JOptionPane.showMessageDialog(null, "CPF Invalido, insira novamente");
				CPF = JOptionPane.showInputDialog("CPF");
			}

			telefone = JOptionPane.showInputDialog("Telefone");
			email = JOptionPane.showInputDialog("E-mail");

			cliente.editaCliente(recebeID, nomeCliente, CPF, telefone, email);
		}
	}

	public void novoPedido() throws ParseException {

		int opc = 0, recebeID;
		double peso, valor, valorTotal = 0.0;
		Date data;
		String recebeData, dataColeta, dataDevolucao, tipoPedido = "", status = "";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		recebeID = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cliente"));

		if (cliente.verificarPosicao(recebeID) == false) {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado, favor cadastrar e iniciar o pedido novamente");
		} else {

			while (opc != 1 & opc != 2) {
				opc = Integer.parseInt(
						JOptionPane.showInputDialog("Digite 1 para pedidos normais ou 2 para pedidos urgentes"));
				switch (opc) {
				case 1:
					tipoPedido = "Normal";
					break;
				case 2:
					tipoPedido = "Urgente";
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção invalida, escolha novamente");
				}
			}

			peso = Integer.parseInt(JOptionPane.showInputDialog("Peso total das peças"));

			valor = Double.parseDouble(JOptionPane.showInputDialog("Valor cobrado por kg de peças"));

			valorTotal = verificarValorTotal(tipoPedido, peso, valorTotal, valor);

			recebeData = JOptionPane.showInputDialog("Data do pedido");

			data = formato.parse(recebeData);
			dataColeta = formato.format(data);

			recebeData = JOptionPane.showInputDialog("Data de retirada");

			data = formato.parse(recebeData);
			dataDevolucao = formato.format(data);

			dataColeta.compareTo(dataDevolucao);

			status = "Ativo";

			pedido.adicionaPedidos(idPedido, recebeID, tipoPedido, peso, valorTotal, dataColeta, dataDevolucao, status);

			JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!\n\nValor do pedido é de R$ "
					+ valorTotal + "\nO ID do seu pedido é: " + idPedido);

			idPedido++;
		}
	}

	public double verificarValorTotal(String tipoPedido, double peso, double valorTotal, double valor) {

		double acresUrg = 1.15;

		if (tipoPedido == "Normal") {
			valorTotal = valor * peso;
		} else {
			valorTotal = (valor * acresUrg) * peso;
		}

		return valorTotal;
	}

	public void buscarPedido() {
		int busca;
		busca = Integer.parseInt(JOptionPane.showInputDialog("Infomar o ID do pedido"));
		pedido.exibeUmPedido(busca);
	}

	public void exibirTodosPedidos() {

		pedido.exibeTodosPedidos();
	}

	public void cancelarPedido() {

		int busca;
		busca = Integer.parseInt(JOptionPane.showInputDialog("Infomar o ID do pedido"));
		pedido.cancelarPedido(busca);

	}
}

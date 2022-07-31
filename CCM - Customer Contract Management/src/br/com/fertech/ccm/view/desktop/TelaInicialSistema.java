package br.com.fertech.ccm.view.desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class TelaInicialSistema extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialSistema frame = new TelaInicialSistema();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicialSistema() {
		setTitle("CCM - Customer Contract Management");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n][][100px:n][grow]", "[][][][][][][]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		contentPane.add(panel, "cell 0 0 4 1,grow");
		
		JLabel lblNewLabel = new JLabel("Logo");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da Empresa");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\prancheta.png"));
		contentPane.add(lblNewLabel_5, "flowx,cell 0 1");
		
		JLabel lblNewLabel_4 = new JLabel("Administra\u00E7\u00E3o");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel_4, "cell 0 1");
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\aperto de m\u00E3o.png"));
		contentPane.add(lblNewLabel_8, "flowx,cell 2 1");
		
		JLabel labelContratos = new JLabel("Contratos");
		labelContratos.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(labelContratos, "cell 2 1");
		
		JButton botaoClientes = new JButton("Clientes");
		botaoClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.setVisible(true);
				dispose();
			}
		});
		botaoClientes.setIcon(null);
		contentPane.add(botaoClientes, "cell 0 2,growx");
		
		JButton botaoConsultar = new JButton("Consultar");
		contentPane.add(botaoConsultar, "cell 2 2,growx");
		
		JButton botaoProjetos = new JButton("Projetos");
		botaoProjetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProjeto tcp = new TelaCadastroProjeto();
				tcp.setVisible(true);
				dispose();
			}
		});
		botaoProjetos.setIcon(null);
		contentPane.add(botaoProjetos, "cell 0 3,growx");
		
		JButton botaoFuncionarios = new JButton("Funcion\u00E1rios");
		botaoFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
				tcf.setVisible(true);
				dispose();
			}
		});
		botaoFuncionarios.setIcon(null);
		contentPane.add(botaoFuncionarios, "cell 0 4,growx");
		
		JButton botaoFechar = new JButton("Deslogar");
		botaoFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja encerrar o programa?");
				if(opcao == 0) {
					TelaPrincipal tp = new TelaPrincipal();
					tp.setVisible(true);
							
					dispose();
				}
			}
		});
		contentPane.add(botaoFechar, "cell 3 5,alignx right");
		
		JButton botaoCadastrarUsuario = new JButton("<html>\r\n<p text-align: center>Cadastrar novo login</p>\r\n<p text-align: center>para acesso ao sistema</p>\r\n");
		botaoCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroLogin tcl = new TelaCadastroLogin();
				tcl.setVisible(true);
				dispose();
			}
		});
		contentPane.add(botaoCadastrarUsuario, "cell 3 6,alignx right");
		
		
	}

}

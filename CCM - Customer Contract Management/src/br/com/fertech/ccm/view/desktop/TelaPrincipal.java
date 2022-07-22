package br.com.fertech.ccm.view.desktop;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxSession.Close;

import net.miginfocom.swing.MigLayout;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textoUsuario;
	private JTextField textoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuCadastrar = new JMenu("Cadastrar");
		menuBar.add(menuCadastrar);
		
		JMenuItem menuItemCliente = new JMenuItem("Cliente");
		menuItemCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.setVisible(true);				
			}
		});
		menuCadastrar.add(menuItemCliente);
		
		JMenuItem menuItemProjeto = new JMenuItem("Projeto");
		menuItemProjeto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProjeto tcp = new TelaCadastroProjeto();
				tcp.setVisible(true);
			}
		});
		
		menuCadastrar.add(menuItemProjeto);
		
		JMenuItem menuItemFuncionario = new JMenuItem("Funcionario");
		menuItemFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
				tcf.setVisible(true);
			}
		});
		
		menuCadastrar.add(menuItemFuncionario);
		
		JMenu menuLista = new JMenu("Listas");
		menuBar.add(menuLista);
		
		JMenuItem menuListaCliente = new JMenuItem("Cliente");
		menuListaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaCliente tlc = new TelaListaCliente();
				tlc.setVisible(true);
			}
		});
		menuLista.add(menuListaCliente);
		
		JMenuItem menuListaProjeto = new JMenuItem("Projeto");
		menuListaProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaProjeto tlp = new TelaListaProjeto();
				tlp.setVisible(true);
			}
		});
		menuLista.add(menuListaProjeto);
		
		JMenuItem menuListaFuncionario = new JMenuItem("Funcionario");
		menuListaFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaFuncionario tlf = new TelaListaFuncionario();
				tlf.setVisible(true);
			}
		});
		menuLista.add(menuListaFuncionario);
		
		JLabel lblNewLabel_2 = new JLabel("excluir barra no futuro");
		menuBar.add(lblNewLabel_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][grow]", "[][][grow][][][][][][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("CCM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 2 0,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Customer Contract Management\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1, "cell 2 1,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Realize o login");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel_3, "cell 2 3,alignx center");
		
		JLabel lblNewLabel_4 = new JLabel("Usu\u00E1rio");
		contentPane.add(lblNewLabel_4, "cell 1 4,alignx trailing");
		
		textoUsuario = new JTextField();
		contentPane.add(textoUsuario, "cell 2 4,growx");
		textoUsuario.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Senha");
		contentPane.add(lblNewLabel_5, "cell 1 5,alignx trailing");
		
		textoSenha = new JTextField();
		contentPane.add(textoSenha, "cell 2 5,growx");
		textoSenha.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Esqueci a senha");
		contentPane.add(lblNewLabel_6, "cell 2 6,alignx right");
		
		JButton botaoLogin = new JButton("Login");
		contentPane.add(botaoLogin, "cell 2 7,growx");
		
		JButton botaoFechar = new JButton("Fechar programa");
		botaoFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja encerrar o programa?");
				if(opcao == 0) {
					System.exit(0);
				}
			}
		});
		contentPane.add(botaoFechar, "cell 2 9,alignx right");
	}

}

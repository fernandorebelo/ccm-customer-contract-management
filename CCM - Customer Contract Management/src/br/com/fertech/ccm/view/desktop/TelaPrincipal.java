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

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][grow][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("CCM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0 4 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Customer Contract Management\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1, "cell 0 1 4 1,alignx center");
		
		JButton botaoFechar = new JButton("Fechar programa");
		botaoFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja encerrar o programa?");
				if(opcao == 0) {
					System.exit(0);
				}
			}
		});
		contentPane.add(botaoFechar, "cell 3 4,growx");
		
		JLabel labelCriador = new JLabel("Criado por Fernando Rebelo");
		contentPane.add(labelCriador, "cell 0 5 4 1,alignx center");
	}

}

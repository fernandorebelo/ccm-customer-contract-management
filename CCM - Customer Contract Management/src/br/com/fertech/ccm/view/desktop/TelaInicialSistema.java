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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n][grow]", "[][][][][][]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		contentPane.add(panel, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel = new JLabel("Logo");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da Empresa");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel(" | ");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("CCM - Customer Contract Management");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Cadastros");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel_4, "cell 0 1");
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\prancheta.png"));
		contentPane.add(lblNewLabel_5, "flowx,cell 0 2");
		
		JButton botaoClientes = new JButton("Clientes");
		botaoClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente tcc = new TelaCadastroCliente();
				tcc.setVisible(true);
			}
		});
		botaoClientes.setIcon(null);
		contentPane.add(botaoClientes, "cell 0 2,growx");
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\prancheta.png"));
		contentPane.add(lblNewLabel_6, "flowx,cell 0 3");
		
		JButton botaoProjetos = new JButton("Projetos");
		botaoProjetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProjeto tcp = new TelaCadastroProjeto();
				tcp.setVisible(true);
			}
		});
		botaoProjetos.setIcon(null);
		contentPane.add(botaoProjetos, "cell 0 3,growx");
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\prancheta.png"));
		contentPane.add(lblNewLabel_7, "flowx,cell 0 4");
		
		JButton botaoFuncionarios = new JButton("Funcion\u00E1rios");
		botaoFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
				tcf.setVisible(true);
			}
		});
		botaoFuncionarios.setIcon(null);
		contentPane.add(botaoFuncionarios, "cell 0 4,growx");
		
		JButton botaoFechar = new JButton("Deslogar");
		botaoFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja encerrar o programa?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		contentPane.add(botaoFechar, "cell 1 5,alignx right");
		
		
	}

}

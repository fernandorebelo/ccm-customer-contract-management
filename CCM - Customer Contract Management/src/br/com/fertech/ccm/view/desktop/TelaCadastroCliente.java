package br.com.fertech.ccm.view.desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.fertech.ccm.core.dao.ClienteDAO;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textoNome;
	private JTextField textoCpf;
	private JTextField textoEndereco;
	private JTextField textoTelefone;
	private JTextField textoEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow][100px:n,grow][100px:n,grow]", "[][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("CADASTRO DE CLIENTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,alignx center");
		
		JLabel labelNome = new JLabel("Nome completo");
		contentPane.add(labelNome, "cell 0 1,alignx left");
		
		textoNome = new JTextField();
		contentPane.add(textoNome, "cell 1 1,growx");
		textoNome.setColumns(10);
		
		JLabel labelCpf = new JLabel("CPF");
		contentPane.add(labelCpf, "cell 0 2,alignx left");
		
		textoCpf = new JTextField();
		contentPane.add(textoCpf, "cell 1 2,growx");
		textoCpf.setColumns(10);
		
		JLabel labelEndereco = new JLabel("Endere\u00E7o");
		contentPane.add(labelEndereco, "cell 0 3,alignx left");
		
		textoEndereco = new JTextField();
		contentPane.add(textoEndereco, "cell 1 3,growx");
		textoEndereco.setColumns(10);
		
		JLabel labelTelefone = new JLabel("Telefone");
		contentPane.add(labelTelefone, "cell 0 4,alignx left");
		
		textoTelefone = new JTextField();
		contentPane.add(textoTelefone, "cell 1 4,growx");
		textoTelefone.setColumns(10);
		
		JLabel labelEmail = new JLabel("Email");
		contentPane.add(labelEmail, "cell 0 5,alignx left");
		
		textoEmail = new JTextField();
		textoEmail.setToolTipText("");
		contentPane.add(textoEmail, "cell 1 5,growx");
		textoEmail.setColumns(10);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmarCadastro = JOptionPane.showConfirmDialog(null, "Deseja confirmar o cadastro?");
				if(confirmarCadastro == 0) {
					ClienteEntity clienteEntity = new ClienteEntity();
					clienteEntity.setNome(textoNome.getText());
					clienteEntity.setCpf(textoCpf.getText());
					clienteEntity.setEndereco(textoEndereco.getText());
					clienteEntity.setTelefone(textoTelefone.getText());
					clienteEntity.setEmail(textoEmail.getText());
					
					try {
						ClienteService cs = new ClienteService();
						cs.salvarCliente(clienteEntity);
						JOptionPane.showMessageDialog(null, "Cliente " + textoNome.getText() + " cadastrado com sucesso.");
						limparCampos();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
				}
			}
		});
		contentPane.add(botaoCadastrar, "cell 1 7,growx");
		
		JButton botaoLimpar = new JButton("Limpar campos");
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "cell 2 7,growx");
		
		JButton botaoSair = new JButton("Voltar");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		contentPane.add(botaoSair, "cell 1 9,growx");
	}
	
	public void limparCampos() {
		textoNome.setText("");
		textoCpf.setText("");
		textoEndereco.setText("");
		textoTelefone.setText("");
		textoEmail.setText("");
	}

}

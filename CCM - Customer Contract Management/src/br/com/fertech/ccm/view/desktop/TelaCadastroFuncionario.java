package br.com.fertech.ccm.view.desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.FuncionarioService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField textoNome;
	private JTextField textoCargo;
	private JTextField textoRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
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
	public TelaCadastroFuncionario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow][100px:n,grow][100px:n,grow]", "[][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("CADASTRO FUNCIONARIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,alignx center");
		
		JLabel labelNome = new JLabel("Nome");
		contentPane.add(labelNome, "cell 0 1,alignx left");
		
		textoNome = new JTextField();
		contentPane.add(textoNome, "cell 1 1,growx");
		textoNome.setColumns(10);
		
		JLabel labelCargo = new JLabel("Cargo");
		contentPane.add(labelCargo, "cell 0 2,alignx left");
		
		textoCargo = new JTextField();
		contentPane.add(textoCargo, "cell 1 2,growx");
		textoCargo.setColumns(10);
		
		JLabel labelRegistro = new JLabel("Registro profissional");
		contentPane.add(labelRegistro, "cell 0 3,alignx left");
		
		textoRegistro = new JTextField();
		contentPane.add(textoRegistro, "cell 1 3,growx");
		textoRegistro.setColumns(10);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmarCadastro = JOptionPane.showConfirmDialog(null, "Deseja confirmar o cadastro?");
				if(confirmarCadastro == 0) {
					FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
					funcionarioEntity.setNome(textoNome.getText());
					funcionarioEntity.setCargo(textoCargo.getText());
					funcionarioEntity.setRegistroProfissional(textoRegistro.getText());
					
					try {
						FuncionarioService fs = new FuncionarioService();
						fs.salvarFuncionario(funcionarioEntity);
						JOptionPane.showMessageDialog(null, "Funcionário " + textoNome.getText() + " cadastrado com sucesso.");
						limparCampos();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
				}
			}
		});
		contentPane.add(botaoCadastrar, "cell 1 5,growx");
		
		JButton botaoLimpar = new JButton("Limpar campos");
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "cell 2 5,growx");
		
		JButton botaoSair = new JButton("Voltar");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		contentPane.add(botaoSair, "cell 1 7,growx");
	}

	public void limparCampos() {
		textoNome.setText("");
		textoCargo.setText("");
		textoRegistro.setText("");
	}
}

package br.com.fertech.ccm.view.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.service.FuncionarioService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textoLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroLogin frame = new TelaCadastroLogin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroLogin() {
		setTitle("CCM - Customer Contract Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblNewLabel_2 = new JLabel("Novo cadastro de usu\u00E1rio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel_2, "cell 0 1 2 1,alignx left");
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		contentPane.add(lblNewLabel_3, "cell 0 2,alignx right");
		
		textoLogin = new JTextField();
		contentPane.add(textoLogin, "cell 1 2,growx");
		textoLogin.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Senha");
		contentPane.add(lblNewLabel_4, "cell 0 3,alignx right");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "cell 1 3,growx");
		
		JButton botaoCadastrar = new JButton("Cadastrar novo usu\u00E1rio");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioService fs = new FuncionarioService();
				String strLogin = textoLogin.getText();
				String strSenha = new String(passwordField.getPassword());
				
				try {
					if(!fs.autenticarLoginFuncionario(strLogin, strSenha)) {
						FuncionarioEntity fe = new FuncionarioEntity();
						fe.setLogin(textoLogin.getText());
						fe.setSenha(new String(passwordField.getPassword()));
						fs.salvarLoginFuncionario(fe);
						JOptionPane.showMessageDialog(null, "Novo cadastro realizado com sucesso.");
						textoLogin.setText("");
						passwordField.setText("");
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Login já existe. Tente outro.");
						textoLogin.setText("");
						passwordField.setText("");
					}
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(botaoCadastrar, "cell 1 4");
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicialSistema tis = new TelaInicialSistema();
				tis.setVisible(true);
				dispose();
			}
		});
		contentPane.add(botaoVoltar, "cell 1 5,alignx right");
	}

}

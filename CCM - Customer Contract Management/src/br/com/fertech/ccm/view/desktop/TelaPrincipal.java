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

import br.com.fertech.ccm.core.dao.UsuarioDAO;
import br.com.fertech.ccm.core.entity.UsuarioEntity;
import br.com.fertech.ccm.core.service.UsuarioService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textoLogin;
	private JPasswordField passwordField;

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
		setTitle("CCM - Customer Contract Management");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][grow]", "[][][grow][][][][][][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("CCM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 2 0,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Customer Contract Management\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1, "cell 2 1,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Realize o login");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel_3, "cell 2 3,alignx center");
		
		JLabel lblNewLabel_4 = new JLabel("Login");
		contentPane.add(lblNewLabel_4, "cell 1 4,alignx trailing");
		
		textoLogin = new JTextField();
		contentPane.add(textoLogin, "cell 2 4,growx");
		textoLogin.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Senha");
		contentPane.add(lblNewLabel_5, "cell 1 5,alignx trailing");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "cell 2 5,growx");
		
		JLabel lblNewLabel_6 = new JLabel("Esqueci a senha");
		contentPane.add(lblNewLabel_6, "cell 2 6,alignx right");
		
		JButton botaoLogin = new JButton("Login");
		botaoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioService us = new UsuarioService();
				String strLogin = textoLogin.getText();
				String strSenha = new String(passwordField.getPassword());
				
				try {
					if(us.autenticarUsuario(strLogin, strSenha)) {
						TelaInicialSistema tela = new TelaInicialSistema();
						tela.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos.");
					}
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(botaoLogin, "cell 2 7,growx");
		
		JLabel lblNewLabel_2 = new JLabel("<html>\r\n<p>Dados para login</p>\r\n<p>Login: admin</p>\r\n<p>Senha: admin</p>");
		contentPane.add(lblNewLabel_2, "cell 3 8,alignx left");
	}

}

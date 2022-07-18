package br.com.fertech.ccm.view.desktop;

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

import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.ProjetoService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroProjeto extends JFrame {

	private JPanel contentPane;
	private JTextField textoTipoProjeto;
	private JTextField textoAmbiente;
	private JTextField textoArea;
	private JTextField textoValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProjeto frame = new TelaCadastroProjeto();
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
	public TelaCadastroProjeto() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow][100px:n,grow][100px:n,grow]", "[fill][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("CADASTRO PROJETO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,alignx center");
		
		JLabel labelTipoProjeto = new JLabel("Tipo de projeto");
		contentPane.add(labelTipoProjeto, "cell 0 1,alignx left");
		
		textoTipoProjeto = new JTextField();
		contentPane.add(textoTipoProjeto, "cell 1 1,growx");
		textoTipoProjeto.setColumns(10);
		
		JLabel labelAmbiente = new JLabel("Ambiente");
		contentPane.add(labelAmbiente, "cell 0 2,alignx left");
		
		textoAmbiente = new JTextField();
		contentPane.add(textoAmbiente, "cell 1 2,growx");
		textoAmbiente.setColumns(10);
		
		JLabel labelArea = new JLabel("\u00C1rea");
		contentPane.add(labelArea, "cell 0 3,alignx left");
		
		textoArea = new JTextField();
		textoArea.setText("0.0");
		contentPane.add(textoArea, "cell 1 3,growx");
		textoArea.setColumns(10);
		
		JLabel labelValor = new JLabel("Valor");
		contentPane.add(labelValor, "cell 0 4,alignx left");
		
		textoValor = new JTextField();
		textoValor.setText("0.0");
		contentPane.add(textoValor, "cell 1 4,growx");
		textoValor.setColumns(10);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmarCadastro = JOptionPane.showConfirmDialog(null, "Deseja confirmar o cadastro?");
				if(confirmarCadastro == 0) {
					ProjetoEntity projetoEntity = new ProjetoEntity();
					projetoEntity.setTipoProjeto(textoTipoProjeto.getText());
					projetoEntity.setAmbiente(textoAmbiente.getText());
					projetoEntity.setArea(Double.parseDouble(textoArea.getText()));
					projetoEntity.setValor(Double.parseDouble(textoValor.getText()));
					
					try {
						ProjetoService ps = new ProjetoService();
						ps.salvarProjeto(projetoEntity);
						JOptionPane.showMessageDialog(null, "O " + textoTipoProjeto.getText() + " foi cadastrado com sucesso.");
						limparCampos();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
				}
			}
		});
		contentPane.add(botaoCadastrar, "cell 1 6,growx");
		
		JButton botaoLimpar = new JButton("Limpar campos");
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "cell 2 6,growx");
		
		JButton botaoSair = new JButton("Voltar");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		contentPane.add(botaoSair, "cell 1 8,growx");
	}
	
	public void limparCampos() {
		textoTipoProjeto.setText("");
		textoAmbiente.setText("");
		textoArea.setText("");
		textoValor.setText("");
	}

}

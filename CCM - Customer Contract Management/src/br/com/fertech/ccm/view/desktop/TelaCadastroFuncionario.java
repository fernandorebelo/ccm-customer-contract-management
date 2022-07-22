package br.com.fertech.ccm.view.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.FuncionarioService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField textoNome;
	private JTextField textoCargo;
	private JTextField textoRegistro;
	private JTable table;
	private List<FuncionarioEntity> funcionarios;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][200px:n][]", "[][][][][][][][][][]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		contentPane.add(panel, "cell 0 0 3 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Logo");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome da Empresa");
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_3 = new JLabel(" | ");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("CCM - Customer Contract Management");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Cadastro de funcion\u00E1rio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 1 3 1,alignx left");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 2 2 1,alignx left,growy");
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\adicionar.png"));
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\salvar.png"));
		panel_1.add(btnNewButton_1);
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioEntity funcionarioSelecionado = funcionarios.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Você deseja excluir o funcionário de código " + funcionarioSelecionado.getCodigoFuncionario());
				if(opcao == 0) {
					try {
						new FuncionarioService().excluirFuncionario(funcionarioSelecionado.getCodigoFuncionario());
						popularTabela();
						botaoExcluir.setEnabled(false);
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					botaoExcluir.setEnabled(false);
				}
				
			}
		});
		botaoExcluir.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\sair.png"));
		panel_1.add(botaoExcluir);
		
		JButton btnNewButton_3 = new JButton("Atualizar");
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\atualizar.png"));
		panel_1.add(btnNewButton_3);
		
		JButton botaoSair = new JButton("Voltar");
		botaoSair.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\retornar.png"));
		panel_1.add(botaoSair);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		
		JLabel labelNome = new JLabel("Nome");
		contentPane.add(labelNome, "cell 0 3,alignx right");
		
		textoNome = new JTextField();
		contentPane.add(textoNome, "cell 1 3,growx");
		textoNome.setColumns(10);
		
		JLabel labelCargo = new JLabel("Cargo");
		contentPane.add(labelCargo, "cell 0 4,alignx right");
		
		textoCargo = new JTextField();
		contentPane.add(textoCargo, "cell 1 4,growx");
		textoCargo.setColumns(10);
		
		JLabel labelRegistro = new JLabel("Registro profissional");
		contentPane.add(labelRegistro, "cell 0 5,alignx right");
		
		textoRegistro = new JTextField();
		contentPane.add(textoRegistro, "cell 1 5,growx");
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
		} ) ;
		
		JLabel textoSituacao = new JLabel("Situa\u00E7\u00E3o");
		contentPane.add(textoSituacao, "cell 0 6,alignx right");
		
		JRadioButton radioSituacaoAtivo = new JRadioButton("Ativo");
		contentPane.add(radioSituacaoAtivo, "flowx,cell 1 6");
		contentPane.add(botaoCadastrar, "cell 1 9,growx");
		
		JRadioButton radioSituacaoInativo = new JRadioButton("Inativo");
		contentPane.add(radioSituacaoInativo, "cell 1 6");
		
		contentPane.add(botaoCadastrar, "cell 1 7,growx");
		
		JButton botaoLimpar = new JButton("Limpar campos");
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		
		
		contentPane.add(botaoLimpar, "cell 2 7,growx");
		
		//tabela
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 9 3 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				botaoExcluir.setEnabled(true);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "NOME", "CARGO", "REGISTRO"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);
		
		popularTabela();
	}
	
	private void popularTabela() {
		try {
			funcionarios = new FuncionarioService().listarFuncionario();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.getDataVector().removeAllElements();
			
			for (FuncionarioEntity funcionarioEntity : funcionarios) {
				model.addRow(new Object[] {
						funcionarioEntity.getCodigoFuncionario(),
						funcionarioEntity.getNome(),
						funcionarioEntity.getCargo(),
						funcionarioEntity.getRegistroProfissional()
				});
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao buscar funcionários no banco de dados: " + e.getMensagemDeErro());
		}
		
	}

	public void limparCampos() {
		textoNome.setText("");
		textoCargo.setText("");
		textoRegistro.setText("");
	}
}

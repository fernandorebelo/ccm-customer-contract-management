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
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.FuncionarioService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<ClienteEntity> clientes;
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][200px:n][]", "[][][][][][][][][][][][grow]"));
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		JButton botaoLimpar = new JButton("Limpar campos");
		JButton botaoCancelar = new JButton("Cancelar");
		JButton botaoExcluir = new JButton("Excluir");
		
		
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
		
		JLabel lblNewLabel = new JLabel("Cadastro de cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 1 3 1,alignx left");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 2 2 1,alignx left,growy");
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoNome.setEnabled(true);
				textoCpf.setEnabled(true);
				textoEndereco.setEnabled(true);
				textoTelefone.setEnabled(true);
				textoEmail.setEnabled(true);
				botaoCancelar.setEnabled(true);
				botaoCancelar.setVisible(true);
				botaoCadastrar.setEnabled(true);
				botaoLimpar.setEnabled(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\adicionar.png"));
		panel_1.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\salvar.png"));
		panel_1.add(btnNewButton_1);
		
		
		botaoExcluir.setEnabled(false);
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Você deseja excluir o funcionário de código " + clienteSelecionado.getCodigoCliente());
				if(opcao == 0) {
					try {
						new ClienteService().excluirCliente(clienteSelecionado.getCodigoCliente());
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
		
		contentPane.add(botaoCancelar, "cell 2 2,growx");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoNome.setEnabled(false);
				textoCpf.setEnabled(false);
				textoEndereco.setEnabled(false);
				textoTelefone.setEnabled(false);
				textoEmail.setEnabled(false);
				botaoCancelar.setEnabled(false);
				botaoCancelar.setVisible(false);
				botaoCadastrar.setEnabled(false);
				botaoLimpar.setEnabled(false);
				botaoExcluir.setEnabled(false);
			}
		});
		botaoCancelar.setVisible(false);
		
		JLabel labelNome = new JLabel("Nome completo");
		contentPane.add(labelNome, "cell 0 3,alignx right");
		
		textoNome = new JTextField();
		textoNome.setEnabled(false);
		contentPane.add(textoNome, "cell 1 3,growx");
		textoNome.setColumns(10);
		
		JLabel labelCpf = new JLabel("CPF");
		contentPane.add(labelCpf, "cell 0 4,alignx right");
		
		textoCpf = new JTextField();
		textoCpf.setEnabled(false);
		contentPane.add(textoCpf, "cell 1 4,growx");
		textoCpf.setColumns(10);
		
		JLabel labelEndereco = new JLabel("Endere\u00E7o");
		contentPane.add(labelEndereco, "cell 0 5,alignx right");
		
		textoEndereco = new JTextField();
		textoEndereco.setEnabled(false);
		contentPane.add(textoEndereco, "cell 1 5,growx");
		textoEndereco.setColumns(10);
		
		JLabel labelTelefone = new JLabel("Telefone");
		contentPane.add(labelTelefone, "cell 0 6,alignx right");
		
		textoTelefone = new JTextField();
		textoTelefone.setEnabled(false);
		contentPane.add(textoTelefone, "cell 1 6,growx");
		textoTelefone.setColumns(10);
		
		JLabel labelEmail = new JLabel("Email");
		contentPane.add(labelEmail, "cell 0 7,alignx right");
		
		textoEmail = new JTextField();
		textoEmail.setEnabled(false);
		textoEmail.setToolTipText("");
		contentPane.add(textoEmail, "cell 1 7,growx");
		textoEmail.setColumns(10);
		
		
		botaoCadastrar.setEnabled(false);
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
		
		JLabel textoSituacao = new JLabel("Situa\u00E7\u00E3o");
		contentPane.add(textoSituacao, "cell 0 8,alignx right");
		
		JRadioButton radioSituacaoAtivo = new JRadioButton("Ativo");
		radioSituacaoAtivo.setEnabled(false);
		contentPane.add(radioSituacaoAtivo, "flowx,cell 1 8");
		contentPane.add(botaoCadastrar, "cell 1 9,growx");
		
		JRadioButton radioSituacaoInativo = new JRadioButton("Inativo");
		radioSituacaoAtivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selecionado = "";
				if(radioSituacaoAtivo.isSelected()) {
					selecionado = "Ativo";
				}else if(radioSituacaoInativo.isSelected()) {
					selecionado = "Inativo";
				}else {
					selecionado = "Situação não indicada";
				}
			}
		});
		radioSituacaoInativo.setEnabled(false);
		contentPane.add(radioSituacaoInativo, "cell 1 8");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioSituacaoAtivo);
		buttonGroup.add(radioSituacaoInativo);
		
		
		botaoLimpar.setEnabled(false);
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "cell 2 9,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 11 3 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "Linha selecionada: " + table.getSelectedRow());
//				ClienteEntity cliente = clientes.get(table.getSelectedRow());
//				JOptionPane.showMessageDialog(null, "Nome do usuário: " + cliente.getNome());
				botaoExcluir.setEnabled(true);
				botaoCancelar.setEnabled(true);
				botaoCancelar.setVisible(true);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "NOME", "CPF", "ENDERECO", "TELEFONE", "E-MAIL"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		} ) ;
		scrollPane.setViewportView(table);
		popularTabela();
	}
	
	public void limparCampos() {
		textoNome.setText("");
		textoCpf.setText("");
		textoEndereco.setText("");
		textoTelefone.setText("");
		textoEmail.setText("");
	}
	
	//método para inserir dados na tabela
		private void popularTabela() {
			try {
				clientes = new ClienteService().listarCliente();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements(); //para não repetir a tabela em algum momento
				
				for (ClienteEntity clienteEntity : clientes) {
					model.addRow(new Object[] {clienteEntity.getCodigoCliente(),
												clienteEntity.getNome(),
												clienteEntity.getCpf(),
												clienteEntity.getEndereco(),
												clienteEntity.getEmail(),
												clienteEntity.getEmail()
							});
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao buscar clientes no banco de dados: " + e.getMensagemDeErro());
			}
		}

}

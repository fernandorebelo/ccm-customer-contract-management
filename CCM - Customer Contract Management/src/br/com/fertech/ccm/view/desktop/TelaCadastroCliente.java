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
	private JTextField textoId;
	
	JButton botaoCadastrar = new JButton("Cadastrar");
	JButton botaoLimpar = new JButton("Limpar campos");
	JButton botaoExcluir = new JButton("Excluir");
	JRadioButton radioSituacaoInativo = new JRadioButton("Inativo");
	JRadioButton radioSituacaoAtivo = new JRadioButton("Ativo");
	JButton botaoEditar = new JButton("Editar");
	JButton botaoSalvar = new JButton("Salvar");
	JButton botaoCancelar = new JButton("Cancelar");
	JButton botaoNovo = new JButton("Novo");
	

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
		contentPane.setLayout(new MigLayout("", "[][200px:n,grow][]", "[][][][][][][][][][][][][][grow]"));
		
		
		botaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				carregarClientePorId(clienteSelecionado.getCodigoCliente());
				ativarCamposEditar();
			}
		});
		
		
		
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
		
		
		botaoNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarCamposNovo();
				
			}
		});
		botaoNovo.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\adicionar.png"));
		panel_1.add(botaoNovo);
		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmarAlteracao = JOptionPane.showConfirmDialog(null, "Deseja confirmar o alteração?");
				if(confirmarAlteracao == 0) {
					ClienteEntity clienteEntity = new ClienteEntity();
					clienteEntity.setCodigoCliente(Long.parseLong(textoId.getText()));
					clienteEntity.setNome(textoNome.getText());
					clienteEntity.setCpf(textoCpf.getText());
					clienteEntity.setEndereco(textoEndereco.getText());
					clienteEntity.setTelefone(textoTelefone.getText());
					clienteEntity.setEmail(textoEmail.getText());
					
					try {
						ClienteService cs = new ClienteService();
						cs.alterarCliente(clienteEntity);
						JOptionPane.showMessageDialog(null, "Cliente " + textoNome.getText() + " alterado com sucesso.");
						limparCampos();
						desativarCampos();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Alteração cancelada.");
					desativarCampos();
				}
			}
		});
		
		
		
		botaoSalvar.setEnabled(false);
		botaoSalvar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\salvar.png"));
		panel_1.add(botaoSalvar);
		
		
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
						desativarCampos();
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
		
		
		botaoEditar.setEnabled(false);
		botaoEditar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\atualizar.png"));
		panel_1.add(botaoEditar);
		
		panel_1.add(botaoCancelar);
		botaoCancelar.setEnabled(false);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				desativarCampos();
				limparCampos();
			}
		});
		
		JButton botaoSair = new JButton("Voltar");
		contentPane.add(botaoSair, "cell 2 2,growx");
		botaoSair.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\retornar.png"));
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		
		JLabel labelId = new JLabel("C\u00F3digo");
		contentPane.add(labelId, "cell 0 3,alignx trailing");
		
		textoId = new JTextField();
		textoId.setEditable(false);
		textoId.setEnabled(false);
		contentPane.add(textoId, "cell 1 3,growx");
		textoId.setColumns(10);
		
		JLabel labelNome = new JLabel("Nome completo");
		contentPane.add(labelNome, "cell 0 4,alignx right");
		
		textoNome = new JTextField();
		textoNome.setEnabled(false);
		contentPane.add(textoNome, "cell 1 4,growx");
		textoNome.setColumns(10);
		
		JLabel labelCpf = new JLabel("CPF");
		contentPane.add(labelCpf, "cell 0 5,alignx right");
		
		textoCpf = new JTextField();
		textoCpf.setEnabled(false);
		contentPane.add(textoCpf, "cell 1 5,growx");
		textoCpf.setColumns(10);
		
		JLabel labelEndereco = new JLabel("Endere\u00E7o");
		contentPane.add(labelEndereco, "cell 0 6,alignx right");
		
		textoEndereco = new JTextField();
		textoEndereco.setEnabled(false);
		contentPane.add(textoEndereco, "cell 1 6,growx");
		textoEndereco.setColumns(10);
		
		JLabel labelTelefone = new JLabel("Telefone");
		contentPane.add(labelTelefone, "cell 0 7,alignx right");
		
		textoTelefone = new JTextField();
		textoTelefone.setEnabled(false);
		contentPane.add(textoTelefone, "cell 1 7,growx");
		textoTelefone.setColumns(10);
		
		JLabel labelEmail = new JLabel("Email");
		contentPane.add(labelEmail, "cell 0 8,alignx right");
		
		textoEmail = new JTextField();
		textoEmail.setEnabled(false);
		textoEmail.setToolTipText("");
		contentPane.add(textoEmail, "cell 1 8,growx");
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
						desativarCampos();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
					desativarCampos();
				}
			}
		});
		
		JLabel textoSituacao = new JLabel("Situa\u00E7\u00E3o");
		contentPane.add(textoSituacao, "cell 0 9,alignx right");
		
		
		radioSituacaoAtivo.setEnabled(false);
		contentPane.add(radioSituacaoAtivo, "flowx,cell 1 9");
		contentPane.add(botaoCadastrar, "cell 1 10,growx");
		
		
		
		radioSituacaoInativo.setEnabled(false);
		contentPane.add(radioSituacaoInativo, "cell 1 9");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioSituacaoAtivo);
		buttonGroup.add(radioSituacaoInativo);
		
		
		botaoLimpar.setEnabled(false);
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "flowx,cell 2 10,growx");
		
		JButton botaoAtualizarTabela = new JButton("Atualizar");
		botaoAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popularTabela();
			}
		});
		contentPane.add(botaoAtualizarTabela, "cell 2 11,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 13 3 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				botaoExcluir.setEnabled(true);
				botaoCancelar.setEnabled(true);
				botaoEditar.setEnabled(true);
				botaoSalvar.setEnabled(true);
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
												clienteEntity.getTelefone(),
												clienteEntity.getEmail()
							});
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao buscar clientes no banco de dados: " + e.getMensagemDeErro());
			}
		}
		

		public void carregarClientePorId(long codigoCliente) {
			try {
				ClienteEntity clienteEncontrado = new ClienteService().buscarClientePorId(codigoCliente);
				
				if(clienteEncontrado == null) {
					JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					textoId.setText(""+clienteEncontrado.getCodigoCliente());
					textoNome.setText(clienteEncontrado.getNome());
					textoCpf.setText(clienteEncontrado.getCpf());
					textoEndereco.setText(clienteEncontrado.getEndereco());
					textoTelefone.setText(clienteEncontrado.getTelefone());
					textoEmail.setText(clienteEncontrado.getEmail());
				}
				
			} catch (BusinessException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public void ativarCamposNovo() {
			textoNome.setEnabled(true);
			textoCpf.setEnabled(true);
			textoEndereco.setEnabled(true);
			textoTelefone.setEnabled(true);
			textoEmail.setEnabled(true);
			botaoCancelar.setEnabled(true);
			botaoCadastrar.setEnabled(true);
			botaoLimpar.setEnabled(true);
			radioSituacaoAtivo.setEnabled(true);
			radioSituacaoInativo.setEnabled(true);
			textoId.setText("");
		}
		
		public void ativarCamposEditar() {
			textoNome.setEnabled(true);
			textoCpf.setEnabled(true);
			textoEndereco.setEnabled(true);
			textoTelefone.setEnabled(true);
			textoEmail.setEnabled(true);
			botaoCancelar.setEnabled(true);
			botaoLimpar.setEnabled(true);
			radioSituacaoAtivo.setEnabled(true);
			radioSituacaoInativo.setEnabled(true);
			botaoNovo.setEnabled(false);
		}
		
		public void desativarCampos() {
			textoNome.setEnabled(false);
			textoCpf.setEnabled(false);
			textoEndereco.setEnabled(false);
			textoTelefone.setEnabled(false);
			textoEmail.setEnabled(false);
			botaoCancelar.setEnabled(false);
			botaoCadastrar.setEnabled(false);
			botaoLimpar.setEnabled(false);
			botaoExcluir.setEnabled(false);
			radioSituacaoAtivo.setEnabled(false);
			radioSituacaoAtivo.setSelected(false);
			radioSituacaoInativo.setEnabled(false);
			radioSituacaoInativo.setSelected(false);
			botaoEditar.setEnabled(false);
			botaoSalvar.setEnabled(false);
			botaoNovo.setEnabled(true);
			textoId.setText("");
		}

}

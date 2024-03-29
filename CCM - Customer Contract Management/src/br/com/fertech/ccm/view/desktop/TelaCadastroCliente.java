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
	JButton botaoEditar = new JButton("Editar");
	JButton botaoSalvar = new JButton("Salvar");
	JButton botaoCancelar = new JButton("Cancelar");
	JButton botaoNovo = new JButton("Novo");
	private JTextField textoFiltroCodigo;
	private JTextField textoFiltroNome;
	private JTextField textoFiltroEmail;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() {
		setTitle("CCM - Customer Contract Management");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][200px:n,grow][][grow]", "[][][][][][][][][][][][][][][][][][grow]"));
		
		
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
		
		JLabel lblNewLabel = new JLabel("Cadastro de cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 1 4 1,alignx left");
		
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
				int confirmarAlteracao = JOptionPane.showConfirmDialog(null, "Deseja confirmar o altera��o?");
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
						popularTabela();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Altera��o cancelada.");
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
				int opcao = JOptionPane.showConfirmDialog(null, "Voc� deseja excluir o funcion�rio de c�digo " + clienteSelecionado.getCodigoCliente());
				if(opcao == 0) {
					try {
						new ClienteService().excluirCliente(clienteSelecionado.getCodigoCliente());
						JOptionPane.showMessageDialog(null, "Exclu�do com sucesso.");
						popularTabela();
						botaoExcluir.setEnabled(false);
						desativarCampos();
						popularTabela();
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
					TelaInicialSistema tis = new TelaInicialSistema();
					tis.setVisible(true);
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
						popularTabela();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
					desativarCampos();
				}
			}
		});
		contentPane.add(botaoCadastrar, "cell 1 10,growx");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JButton botaoAtualizarTabela = new JButton("Atualizar");
		botaoAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popularTabela();
			}
		});
		
		
		botaoLimpar.setEnabled(false);
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "flowx,cell 2 10,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Pesquisar por filtro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_2, "cell 0 11 2 1,alignx left");
		contentPane.add(botaoAtualizarTabela, "cell 2 11,growx");
		
		JLabel lblNewLabel_3 = new JLabel("C\u00F3digo");
		contentPane.add(lblNewLabel_3, "cell 0 12,alignx trailing");
		
		textoFiltroCodigo = new JTextField();
		contentPane.add(textoFiltroCodigo, "cell 1 12,growx");
		textoFiltroCodigo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Nome");
		contentPane.add(lblNewLabel_4, "cell 0 13,alignx trailing");
		
		textoFiltroNome = new JTextField();
		contentPane.add(textoFiltroNome, "cell 1 13,growx");
		textoFiltroNome.setColumns(10);
		
		JButton botaoPesquisar = new JButton("Pesquisar");
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteFiltro = new ClienteEntity();
				clienteFiltro.setNome(textoFiltroNome.getText());
				clienteFiltro.setEmail(textoFiltroEmail.getText());
				
				try {
					if(!textoFiltroCodigo.getText().equals("")) {
						clienteFiltro.setCodigoCliente(Long.parseLong(textoFiltroCodigo.getText()));
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Valor no c�digo precisa ser num�rico.");
				}
				popularTabelaFiltrada(clienteFiltro);
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("E-mail");
		contentPane.add(lblNewLabel_5, "cell 0 14,alignx trailing");
		
		textoFiltroEmail = new JTextField();
		textoFiltroEmail.setText("");
		contentPane.add(textoFiltroEmail, "cell 1 14,growx");
		textoFiltroEmail.setColumns(10);
		contentPane.add(botaoPesquisar, "cell 1 15");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 17 4 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				botaoExcluir.setEnabled(true);
				botaoCancelar.setEnabled(true);
				botaoEditar.setEnabled(true);
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
	
	//m�todo para inserir dados na tabela
		private void popularTabela() {
			try {
				clientes = new ClienteService().listarCliente();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements(); //para n�o repetir a tabela em algum momento
				
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
		
		private void popularTabelaFiltrada(ClienteEntity clienteFiltrado) {
			try {
				clientes = new ClienteService().buscarClienteFiltrado(clienteFiltrado);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements(); //para n�o repetir a tabela em algum momento
				
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
					JOptionPane.showMessageDialog(null, "Cliente n�o encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
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
			botaoNovo.setEnabled(false);
			botaoSalvar.setEnabled(true);
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
			botaoEditar.setEnabled(false);
			botaoSalvar.setEnabled(false);
			botaoNovo.setEnabled(true);
			textoId.setText("");
		}

}

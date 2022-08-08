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

import br.com.fertech.ccm.core.entity.ClienteEntity;
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
	private JTextField textoId;
	
	JButton botaoCancelar = new JButton("Cancelar");
	JButton botaoCadastrar = new JButton("Cadastrar");
	JButton botaoLimpar = new JButton("Limpar campos");
	JButton botaoExcluir = new JButton("Excluir");
	JButton botaoEditar = new JButton("Editar");
	JButton botaoSalvar = new JButton("Salvar");
	JButton botaoNovo = new JButton("Novo");
	private JTextField textoFiltroNome;
	private JTextField textoFiltroCodigo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
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
	public TelaCadastroFuncionario() {
		setTitle("CCM - Customer Contract Management");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][200px:n,grow][][grow]", "[][][][][][][][][][][][][][][][]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		contentPane.add(panel, "cell 0 0 3 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Logo");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome da Empresa");
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Cadastro de funcion\u00E1rio");
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
				int confirmarAlteracao = JOptionPane.showConfirmDialog(null, "Deseja confirmar o alteração?");
				if(confirmarAlteracao == 0) {
					FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
					funcionarioEntity.setCodigoFuncionario(Long.parseLong(textoId.getText()));
					funcionarioEntity.setNome(textoNome.getText());
					funcionarioEntity.setCargo(textoCargo.getText());
					funcionarioEntity.setRegistroProfissional(textoRegistro.getText());
					
					try {
						FuncionarioService fs = new FuncionarioService();
						fs.alterarFuncionario(funcionarioEntity);
						JOptionPane.showMessageDialog(null, "Funcionário " + textoNome.getText() + " alterado com sucesso.");
						limparCampos();
						desativarCampos();
						popularTabela();
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
				FuncionarioEntity funcionarioSelecionado = funcionarios.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Você deseja excluir o funcionário de código " + funcionarioSelecionado.getCodigoFuncionario());
				if(opcao == 0) {
					try {
						new FuncionarioService().excluirFuncionario(funcionarioSelecionado.getCodigoFuncionario());
						JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
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
		botaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioEntity funcionarioSelecionado = funcionarios.get(table.getSelectedRow());
				carregarFuncionarioPorId(funcionarioSelecionado.getCodigoFuncionario());
				ativarCamposEditar();
			}
		});
		
		
		botaoEditar.setEnabled(false);
		botaoEditar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\ccm-customer-contract-management\\CCM - Customer Contract Management\\assets\\atualizar.png"));
		panel_1.add(botaoEditar);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativarCampos();
				limparCampos();
			}
		});
		
		
		botaoCancelar.setEnabled(false);
		panel_1.add(botaoCancelar);
		
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
		
		JLabel labelCodigo = new JLabel("C\u00F3digo");
		contentPane.add(labelCodigo, "cell 0 3,alignx trailing");
		
		textoId = new JTextField();
		textoId.setEnabled(false);
		textoId.setEditable(false);
		contentPane.add(textoId, "cell 1 3,growx");
		textoId.setColumns(10);
		
		JLabel labelNome = new JLabel("Nome");
		contentPane.add(labelNome, "cell 0 4,alignx right");
		
		textoNome = new JTextField();
		textoNome.setEnabled(false);
		contentPane.add(textoNome, "cell 1 4,growx");
		textoNome.setColumns(10);
		
		JLabel labelCargo = new JLabel("Cargo");
		contentPane.add(labelCargo, "cell 0 5,alignx right");
		
		textoCargo = new JTextField();
		textoCargo.setEnabled(false);
		contentPane.add(textoCargo, "cell 1 5,growx");
		textoCargo.setColumns(10);
		
		JLabel labelRegistro = new JLabel("Registro profissional");
		contentPane.add(labelRegistro, "cell 0 6,alignx right");
		
		textoRegistro = new JTextField();
		textoRegistro.setEnabled(false);
		contentPane.add(textoRegistro, "cell 1 6,growx");
		textoRegistro.setColumns(10);
		
		
		botaoCadastrar.setEnabled(false);
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
		} ) ;
		contentPane.add(botaoCadastrar, "cell 1 9,growx");
		
		contentPane.add(botaoCadastrar, "cell 1 7,growx");
		
		
		botaoLimpar.setEnabled(false);
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		
		
		contentPane.add(botaoLimpar, "cell 2 7,growx");
		
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popularTabela();
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Pesquisar por filtro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel_2, "cell 0 10");
		contentPane.add(botaoAtualizar, "cell 2 10,growx");
		
		JLabel lblNewLabel_4 = new JLabel("C\u00F3digo");
		contentPane.add(lblNewLabel_4, "cell 0 11,alignx trailing");
		
		textoFiltroCodigo = new JTextField();
		contentPane.add(textoFiltroCodigo, "cell 1 11,growx");
		textoFiltroCodigo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		contentPane.add(lblNewLabel_3, "cell 0 12,alignx trailing");
		
		textoFiltroNome = new JTextField();
		contentPane.add(textoFiltroNome, "cell 1 12,growx");
		textoFiltroNome.setColumns(10);
		
		JButton botaoPesquisar = new JButton("Pesquisar");
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioEntity funcionarioFiltro = new FuncionarioEntity();
				funcionarioFiltro.setNome(textoFiltroNome.getText());
				
				try {
					if(!textoFiltroCodigo.getText().equals("")) {
						funcionarioFiltro.setCodigoFuncionario(Long.parseLong(textoFiltroCodigo.getText()));
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Valor no código precisa ser numérico.");
				}
				
				popularTabelaFiltrada(funcionarioFiltro);
				
			}
		});
		contentPane.add(botaoPesquisar, "cell 1 13");
		
		//tabela
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 15 4 1,grow");
		
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
	
	private void popularTabelaFiltrada(FuncionarioEntity funcionarioFiltrado) {
		try {
			funcionarios = new FuncionarioService().buscarFuncionarioFiltrado(funcionarioFiltrado);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.getDataVector().removeAllElements(); //para não repetir a tabela em algum momento
			
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
	
	public void carregarFuncionarioPorId(long codigoCliente) {
		try {
			FuncionarioEntity funcionarioEncontrado = new FuncionarioService().buscarFuncionarioPorId(codigoCliente);
			
			if(funcionarioEncontrado == null) {
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				textoId.setText(""+funcionarioEncontrado.getCodigoFuncionario());
				textoNome.setText(funcionarioEncontrado.getNome());
				textoCargo.setText(funcionarioEncontrado.getCargo());
				textoRegistro.setText(funcionarioEncontrado.getRegistroProfissional());
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void limparCampos() {
		textoNome.setText("");
		textoCargo.setText("");
		textoRegistro.setText("");
	}
	
	public void ativarCamposNovo() {
		textoNome.setEnabled(true);
		textoCargo.setEnabled(true);
		textoRegistro.setEnabled(true);
		botaoCancelar.setEnabled(true);
		botaoCadastrar.setEnabled(true);
		botaoLimpar.setEnabled(true);
		textoId.setText("");
	}
	
	public void ativarCamposEditar() {
		textoNome.setEnabled(true);
		textoCargo.setEnabled(true);
		textoRegistro.setEnabled(true);
		botaoCancelar.setEnabled(true);
		botaoLimpar.setEnabled(true);
		botaoNovo.setEnabled(false);
		botaoSalvar.setEnabled(true);
	}
	
	public void desativarCampos() {
		textoNome.setEnabled(false);
		textoCargo.setEnabled(false);
		textoRegistro.setEnabled(false);
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

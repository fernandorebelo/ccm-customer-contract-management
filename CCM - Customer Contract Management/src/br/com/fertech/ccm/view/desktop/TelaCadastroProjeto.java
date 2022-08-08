package br.com.fertech.ccm.view.desktop;

import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.ProjetoService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TelaCadastroProjeto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<ProjetoEntity> projetos;
	private JTextField textoTipoProjeto;
	private JTextField textoAmbiente;
	private JTextField textoArea;
	private JTextField textoValor;
	private JTextField textoId;
	
	JButton botaoCadastrar = new JButton("Cadastrar");
	JButton botaoCancelar = new JButton("Cancelar");
	JButton botaoLimpar = new JButton("Limpar campos");
	JButton botaoNovo = new JButton("Novo");
	JButton botaoExcluir = new JButton("Excluir");
	JButton botaoEditar = new JButton("Editar");
	JButton botaoSalvar = new JButton("Salvar");
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroProjeto frame = new TelaCadastroProjeto();
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
	public TelaCadastroProjeto() {
		setTitle("CCM - Customer Contract Management");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][200px:n][][grow]", "[][][][][][][][][][][][][grow]"));
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		contentPane.add(panel, "cell 0 0 3 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Logo");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome da Empresa");
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Cadastro de projetos");
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
					ProjetoEntity projetoEntity = new ProjetoEntity();
					projetoEntity.setCodigo(Long.parseLong(textoId.getText()));
					projetoEntity.setTipoProjeto(textoTipoProjeto.getText());
					projetoEntity.setAmbiente(textoAmbiente.getText());
					projetoEntity.setArea(Double.parseDouble(textoArea.getText()));
					projetoEntity.setValor(Double.parseDouble(textoValor.getText()));
					
					try {
						ProjetoService ps = new ProjetoService();
						ps.alterarProjeto(projetoEntity);
						JOptionPane.showMessageDialog(null, textoTipoProjeto.getText() + " alterado com sucesso.");
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
				ProjetoEntity projetoSelecionado = projetos.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Você deseja excluir o projeto de código " + projetoSelecionado.getCodigo());
				if(opcao == 0) {
					try {
						new ProjetoService().excluirProjeto(projetoSelecionado.getCodigo());
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
				ProjetoEntity projetoSelecionado = projetos.get(table.getSelectedRow());
				carregarProjetoPorId(projetoSelecionado.getCodigo());
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
		
		JLabel labelTipoProjeto = new JLabel("Tipo de projeto");
		contentPane.add(labelTipoProjeto, "cell 0 4,alignx right");
		
		textoTipoProjeto = new JTextField();
		textoTipoProjeto.setEnabled(false);
		contentPane.add(textoTipoProjeto, "cell 1 4,growx");
		textoTipoProjeto.setColumns(10);
		
		JLabel labelAmbiente = new JLabel("Ambiente");
		contentPane.add(labelAmbiente, "cell 0 5,alignx right");
		
		textoAmbiente = new JTextField();
		textoAmbiente.setEnabled(false);
		contentPane.add(textoAmbiente, "cell 1 5,growx");
		textoAmbiente.setColumns(10);
		
		JLabel labelArea = new JLabel("\u00C1rea");
		contentPane.add(labelArea, "cell 0 6,alignx right");
		
		textoArea = new JTextField();
		textoArea.setEnabled(false);
		textoArea.setText("0.0");
		contentPane.add(textoArea, "cell 1 6,growx");
		textoArea.setColumns(10);
		
		JLabel labelValor = new JLabel("Valor");
		contentPane.add(labelValor, "cell 0 7,alignx right");
		
		textoValor = new JTextField();
		textoValor.setEnabled(false);
		textoValor.setText("0.0");
		contentPane.add(textoValor, "cell 1 7,growx");
		textoValor.setColumns(10);
		
		
		botaoCadastrar.setEnabled(false);
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
						desativarCampos();
						popularTabela();
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
				}
			}
		});
		
		
		contentPane.add(botaoCadastrar, "cell 1 9,growx");
		
		
		
		
		botaoLimpar.setEnabled(false);
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "cell 2 9,growx");
		
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popularTabela();
			}
		});
		contentPane.add(botaoAtualizar, "cell 2 10,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 12 4 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Ativa o botão ao clicar em um campo da tabela
				botaoExcluir.setEnabled(true);
				botaoCancelar.setEnabled(true);
				botaoEditar.setEnabled(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "TIPO DE PROJETO", "AMBIENTE", "AREA", "VALOR"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		popularTabela();
	}
	
		public void limparCampos() {
			textoTipoProjeto.setText("");
			textoAmbiente.setText("");
			textoArea.setText("");
			textoValor.setText("");
		}
		
		private void popularTabela() {
			try {
				projetos = new ProjetoService().listarProjeto();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
				
				for(ProjetoEntity projetoEntity : projetos) {
					model.addRow(new Object[] {
							projetoEntity.getCodigo(), 
							projetoEntity.getTipoProjeto(), 
							projetoEntity.getAmbiente(), 
							projetoEntity.getArea(), 
							projetoEntity.getValor()
							});
				}
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao buscar projetos no banco de dados: " + e.getMensagemDeErro());
			}
		}
		
		public void carregarProjetoPorId(long codigoProjeto) {
			try {
				ProjetoEntity projetoEncontrado = new ProjetoService().buscarProjetoPorId(codigoProjeto);
				
				if(projetoEncontrado == null) {
					JOptionPane.showMessageDialog(null, "Projeto não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					textoId.setText(""+projetoEncontrado.getCodigo());
					textoTipoProjeto.setText(projetoEncontrado.getTipoProjeto());
					textoAmbiente.setText(projetoEncontrado.getAmbiente());
					textoArea.setText(""+projetoEncontrado.getArea());
					textoValor.setText(""+projetoEncontrado.getValor());
				}
				
			} catch (BusinessException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public void ativarCamposNovo() {
			textoTipoProjeto.setEnabled(true);
			textoAmbiente.setEnabled(true);
			textoArea.setEnabled(true);
			textoValor.setEnabled(true);
			botaoCancelar.setEnabled(true);
			botaoCadastrar.setEnabled(true);
			botaoLimpar.setEnabled(true);
			textoId.setText("");
		}
		
		public void ativarCamposEditar() {
			textoTipoProjeto.setEnabled(true);
			textoAmbiente.setEnabled(true);
			textoArea.setEnabled(true);
			textoValor.setEnabled(true);
			botaoCancelar.setEnabled(true);
			botaoLimpar.setEnabled(true);
			botaoNovo.setEnabled(false);
			botaoCadastrar.setEnabled(false);
			botaoSalvar.setEnabled(true);
		}
		
		public void desativarCampos() {
			textoTipoProjeto.setEnabled(false);
			textoAmbiente.setEnabled(false);
			textoArea.setEnabled(false);
			textoValor.setEnabled(false);
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

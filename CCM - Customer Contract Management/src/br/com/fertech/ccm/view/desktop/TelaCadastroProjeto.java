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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][200px:n][]", "[][][][][][][][][][][grow]"));
		
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
		
		JLabel lblNewLabel = new JLabel("Cadastro de projetos");
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
				ProjetoEntity projetoSelecionado = projetos.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Voc? deseja excluir o projeto de c?digo " + projetoSelecionado.getCodigo());
				if(opcao == 0) {
					try {
						new ProjetoService().excluirProjeto(projetoSelecionado.getCodigo());
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
		
		JLabel labelTipoProjeto = new JLabel("Tipo de projeto");
		contentPane.add(labelTipoProjeto, "cell 0 3,alignx right");
		
		textoTipoProjeto = new JTextField();
		contentPane.add(textoTipoProjeto, "cell 1 3,growx");
		textoTipoProjeto.setColumns(10);
		
		JLabel labelAmbiente = new JLabel("Ambiente");
		contentPane.add(labelAmbiente, "cell 0 4,alignx right");
		
		textoAmbiente = new JTextField();
		contentPane.add(textoAmbiente, "cell 1 4,growx");
		textoAmbiente.setColumns(10);
		
		JLabel labelArea = new JLabel("\u00C1rea");
		contentPane.add(labelArea, "cell 0 5,alignx right");
		
		textoArea = new JTextField();
		textoArea.setText("0.0");
		contentPane.add(textoArea, "cell 1 5,growx");
		textoArea.setColumns(10);
		
		JLabel labelValor = new JLabel("Valor");
		contentPane.add(labelValor, "cell 0 6,alignx right");
		
		textoValor = new JTextField();
		textoValor.setText("0.0");
		contentPane.add(textoValor, "cell 1 6,growx");
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
		
		JLabel lblNewLabel_4 = new JLabel("Situa\u00E7\u00E3o");
		contentPane.add(lblNewLabel_4, "cell 0 7,alignx right");
		
		JRadioButton radioSituacaoAtivo = new JRadioButton("Ativo");
		contentPane.add(radioSituacaoAtivo, "flowx,cell 1 7");
		
		
		contentPane.add(botaoCadastrar, "cell 1 8,growx");
		
		
		
		JButton botaoLimpar = new JButton("Limpar campos");
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		contentPane.add(botaoLimpar, "cell 2 8,growx");
		
		JRadioButton radioSituacaoInativo = new JRadioButton("Inativo");
		contentPane.add(radioSituacaoInativo, "cell 1 7");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 10 3 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Ativa o bot?o excluir ao clicar em um campo da tabela
				botaoExcluir.setEnabled(true);
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

}

package br.com.fertech.ccm.view.desktop;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.service.ProjetoService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListaProjeto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<ProjetoEntity> projetos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaProjeto frame = new TelaListaProjeto();
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
	public TelaListaProjeto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("LISTA DE PROJETOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0");
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjetoEntity projetoSelecionado = projetos.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Você deseja excluir o projeto de código " + projetoSelecionado.getCodigo());
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
		botaoExcluir.setEnabled(false);
		contentPane.add(botaoExcluir, "cell 0 1,alignx right");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 3,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Ativa o botão excluir ao clicar em um campo da tabela
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton botaoSair = new JButton("Voltar");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		contentPane.add(botaoSair, "cell 0 4,alignx right");
		popularTabela();
	}

	//Método para isnerir dados na tabela
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

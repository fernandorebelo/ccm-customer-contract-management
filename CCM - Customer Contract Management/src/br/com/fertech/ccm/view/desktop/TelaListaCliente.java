package br.com.fertech.ccm.view.desktop;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.util.exception.BusinessException;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListaCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//instanciar cliente entity
	private List<ClienteEntity> clientes;
	private JButton botaoExcluir;
	private JButton botaoSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaCliente frame = new TelaListaCliente();
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
	public TelaListaCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow][100px:n,grow]", "[][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("LISTA DE CLIENTES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0 2 1");
		
		botaoExcluir = new JButton("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pega o index da linha selecionada na tabela criada
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				int opcao = JOptionPane.showConfirmDialog(null, "Você deseja excluir o cliente de código " + clienteSelecionado.getCodigoCliente());
				if(opcao == 0) {
					try {
						//pega o código do cliente selecionado e exclui com o método excluirCliente
						new ClienteService().excluirCliente(clienteSelecionado.getCodigoCliente());
						//Insere novamente os dados na tabela
						popularTabela();
						//Desativa o botão excluir
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
		contentPane.add(botaoExcluir, "cell 1 1,alignx right");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 3 2 1,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "Linha selecionada: " + table.getSelectedRow());
//				ClienteEntity cliente = clientes.get(table.getSelectedRow());
//				JOptionPane.showMessageDialog(null, "Nome do usuário: " + cliente.getNome());
				botaoExcluir.setEnabled(true);
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
		});
		scrollPane.setViewportView(table);
		
		botaoSair = new JButton("Voltar");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para a tela inicial?");
				if(opcao == 0) {
					dispose();
				}
			}
		});
		contentPane.add(botaoSair, "cell 1 4,alignx right");
		popularTabela();
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

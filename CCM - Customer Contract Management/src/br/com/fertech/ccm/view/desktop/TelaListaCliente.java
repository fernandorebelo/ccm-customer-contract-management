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

public class TelaListaCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//instanciar cliente entity
	private List<ClienteEntity> clientes;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		JLabel lblNewLabel = new JLabel("LISTA DE CLIENTES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2,grow");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "Linha selecionada: " + table.getSelectedRow());
				ClienteEntity cliente = clientes.get(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Nome do usuário: " + cliente.getNome());
				
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

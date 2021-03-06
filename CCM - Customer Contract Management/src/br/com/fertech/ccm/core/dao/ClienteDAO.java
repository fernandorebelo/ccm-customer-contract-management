package br.com.fertech.ccm.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import br.com.fertech.ccm.core.bo.ClienteBO;
import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ClienteDAO {
	
	public void excluirCliente(long cliente) throws BusinessException {
		
		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			// conex?o e criar prepare statement
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			//setar valores
			ps.setLong(1, cliente);
			
			// executar comando
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao excluir cliente.");
		} finally {
			if(ps != null) {
				try {
					//fechar prepared statement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<ClienteEntity> listarCliente() throws BusinessException{
		
		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, TEL_CLIENTE, EMAIL_CLIENTE FROM CLIENTE";
		PreparedStatement ps = null;
		List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
		
		//Ler a tabela no banco de dados
		ResultSet rs = null;
		
		try {
			// conex?o e criar prepare statement
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			//Realizar pesquisa no banco de dados
			rs = ps.executeQuery();
			
			//Ler a pr?xima linha caso seja true
			while(rs.next()) {
			//setar valores
				ClienteEntity cliente = new ClienteEntity();
				cliente.setCodigoCliente(rs.getInt("ID_CLIENTE"));
				cliente.setNome(rs.getString("NM_CLIENTE"));
				cliente.setCpf(rs.getString("CPF_CLIENTE"));
				cliente.setEndereco(rs.getString("END_CLIENTE"));
				cliente.setTelefone(rs.getString("TEL_CLIENTE"));
				cliente.setEmail(rs.getString("EMAIL_CLIENTE"));
				
			//Adicionar ? lista
				clientes.add(cliente);
			}
			
			// executar comando
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar clientes.");
		} finally {
			if(ps != null) {
				try {
					//fechar prepared statement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return clientes;
		
	}

	public String salvarCliente(ClienteEntity cliente)  throws BusinessException{
		System.out.println("Cliente - Camada DAO - Data Access Object...");
		System.out.println("Cliente - Cadastrando no banco de dados...");
		
		// inserir informa??es no banco de dados cliente
		String sql = "INSERT INTO CLIENTE (NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, TEL_CLIENTE, EMAIL_CLIENTE) VALUES (?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			// conex?o e criar prepare statement
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			// setar valores
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEndereco());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getEmail());
			
			// executar comando
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao cadastrar cliente.");
		} finally {
			if(ps != null) {
				try {
					//fechar prepared statement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "Cliente cadastrado.";
	}

}
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
	
	//FILTRAR DE FORMA DINÂMICA
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws BusinessException{
		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, TEL_CLIENTE, EMAIL_CLIENTE FROM CLIENTE";
		
		boolean adicionaWhere = true;
		
		List<ClienteEntity> resultado = new ArrayList<ClienteEntity>();
		
		//sql dinâmico
		if(cliente != null) {
			if(cliente.getCodigoCliente() != null) {
				sql += " WHERE ";
				sql += " ID_CLIENTE = ? ";
				adicionaWhere = false;
			}
			if(cliente.getNome() != null && !cliente.getNome().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " NM_CLIENTE LIKE ?";
			}
			if(cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " CPF_CLIENTE LIKE ?";
			}
			if(cliente.getEndereco() != null && !cliente.getEndereco().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " END_CLIENTE LIKE ?";
			}
			if(cliente.getTelefone() != null && !cliente.getTelefone().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " TEL_CLIENTE LIKE ?";
			}
			if(cliente.getEmail() != null && !cliente.getEmail().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " EMAIL_CLIENTE LIKE ?";
			}
		}
//		System.out.println(sql);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			//setar os valores no SQL de acordo com o índice
			int indice = 0;
			if(cliente != null) {
				if(cliente.getCodigoCliente() != null) {
					indice += 1;
					ps.setLong(indice, cliente.getCodigoCliente());
				}
				if(cliente.getNome() != null && !cliente.getNome().equals("")) {
					indice += 1;
					ps.setString(indice, cliente.getNome());
				}
				if(cliente.getCpf() != null && !cliente.getCpf().equals("")) {
					indice += 1;
					ps.setString(indice, cliente.getCpf());
				}
				if(cliente.getEndereco() != null && !cliente.getEndereco().equals("")) {
					indice += 1;
					ps.setString(indice, cliente.getEndereco());
				}
				if(cliente.getTelefone() != null && !cliente.getTelefone().equals("")) {
					indice += 1;
					ps.setString(indice, cliente.getTelefone());
				}
				if(cliente.getEmail() != null && !cliente.getEmail().equals("")) {
					indice += 1;
					ps.setString(indice, cliente.getEmail());
				}
			}
			
			rs = ps.executeQuery();
			
			//setar valores na entidade
			while(rs.next()) {
				ClienteEntity clienteResultado = new ClienteEntity();
				clienteResultado.setCodigoCliente(rs.getLong("ID_CLIENTE"));
				clienteResultado.setNome(rs.getString("NM_CLIENTE"));
				clienteResultado.setCpf(rs.getString("CPF_CLIENTE"));
				clienteResultado.setEndereco(rs.getString("END_CLIENTE"));
				clienteResultado.setTelefone(rs.getString("TEL_CLIENTE"));
				clienteResultado.setEmail(rs.getString("EMAIL_CLIENTE"));
				resultado.add(clienteResultado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao filtrar os dados do cliente.");
		} finally {
			if(ps != null) {
				try {
					//fechar prepared statement
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return resultado;
	}
	
	//CRUD
	public String alterarCliente(ClienteEntity cliente) throws BusinessException{
		String sql = "UPDATE CLIENTE SET NM_CLIENTE = ?, CPF_CLIENTE = ?, END_CLIENTE = ?, TEL_CLIENTE = ?, EMAIL_CLIENTE = ? WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEndereco());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getEmail());
			ps.setLong(6, cliente.getCodigoCliente());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao atualizar os dados do cliente.");
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
		
		return "Cliente alterado com sucesso";
	}
	
	public ClienteEntity buscarClientePorId(long codigoCliente) throws BusinessException{
		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, TEL_CLIENTE, EMAIL_CLIENTE FROM CLIENTE WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			
			rs = ps.executeQuery();
			
			ClienteEntity clienteEncontrado = null;
			
			if(rs.next()) {
				clienteEncontrado = new ClienteEntity();
				clienteEncontrado.setCodigoCliente(rs.getLong("ID_CLIENTE"));
				clienteEncontrado.setNome(rs.getString("NM_CLIENTE"));
				clienteEncontrado.setCpf(rs.getString("CPF_CLIENTE"));
				clienteEncontrado.setEndereco(rs.getString("END_CLIENTE"));
				clienteEncontrado.setTelefone(rs.getString("TEL_CLIENTE"));
				clienteEncontrado.setEmail(rs.getString("EMAIL_CLIENTE"));
			}
			return clienteEncontrado;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao buscar cliente.");
		} finally {
			if(ps != null) {
				try {
					//fechar prepared statement
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void excluirCliente(long cliente) throws BusinessException {
		
		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			// conexão e criar prepare statement
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
			// conexão e criar prepare statement
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			//Realizar pesquisa no banco de dados
			rs = ps.executeQuery();
			
			//Ler a próxima linha caso seja true
			while(rs.next()) {
			//setar valores
				ClienteEntity cliente = new ClienteEntity();
				cliente.setCodigoCliente(rs.getLong("ID_CLIENTE"));
				cliente.setNome(rs.getString("NM_CLIENTE"));
				cliente.setCpf(rs.getString("CPF_CLIENTE"));
				cliente.setEndereco(rs.getString("END_CLIENTE"));
				cliente.setTelefone(rs.getString("TEL_CLIENTE"));
				cliente.setEmail(rs.getString("EMAIL_CLIENTE"));
				
			//Adicionar à lista
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
		
		// inserir informações no banco de dados cliente
		String sql = "INSERT INTO CLIENTE (NM_CLIENTE, CPF_CLIENTE, END_CLIENTE, TEL_CLIENTE, EMAIL_CLIENTE) VALUES (?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			// conexão e criar prepare statement
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
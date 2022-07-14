package br.com.fertech.ccm.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ClienteDAO {

	public String exibirListaCliente() {
		System.out.println("Cliente - Camada DAO - Puxando lista de clientes...");
		return "Lista de clientes";
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
			throw new BusinessException("Erro ao cadastrar usuario.");
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
		
		//TODO implementar cadastro dessa informação no banco de dados
		return "Cliente cadastrado.";
	}
}

package br.com.fertech.ccm.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class FuncionarioDAO {
	
	public List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		List<FuncionarioEntity> funcionarios = new ArrayList<FuncionarioEntity>();
		String sql = "SELECT ID_FUNCIONARIO, NOME_FUNCIONARIO, CARGO_FUNCIONARIO, REGISTRO_FUNCIONARIO FROM FUNCIONARIO";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FuncionarioEntity funcionario = new FuncionarioEntity();
				funcionario.setCodigoFuncionario(rs.getInt("ID_FUNCIONARIO"));
				funcionario.setNome(rs.getString("NOME_FUNCIONARIO"));
				funcionario.setCargo(rs.getString("CARGO_FUNCIONARIO"));
				funcionario.setRegistroProfissional(rs.getString("REGISTRO_FUNCIONARIO"));
				
				funcionarios.add(funcionario);
			}
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar funcionários.");
		} finally {
			if(ps != null) {
				try {
					//fechar prepared statement
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new BusinessException("Erro ao encerrar a listagem de funcionários.");
				}
			}
		}
		
		
		
		
		return funcionarios;
	}

	public String salvarFuncionario(FuncionarioEntity funcionario) {
		System.out.println("Funcionário - Camada DAO - Data Access Object...");
		System.out.println("Funcionário - Cadastrando no banco de dados...");

		String sql = "INSERT INTO FUNCIONARIO (NOME_FUNCIONARIO, CARGO_FUNCIONARIO, REGISTRO_FUNCIONARIO) VALUES (?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCargo());
			ps.setString(3, funcionario.getRegistroProfissional());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "Funcionário cadastrado.";
	}
}

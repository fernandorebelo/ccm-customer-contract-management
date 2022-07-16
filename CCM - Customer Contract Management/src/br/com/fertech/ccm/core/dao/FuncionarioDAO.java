package br.com.fertech.ccm.core.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;

public class FuncionarioDAO {

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

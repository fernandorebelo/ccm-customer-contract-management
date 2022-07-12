package br.com.fertech.ccm.core.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.ProjetoEntity;

public class ProjetoDAO {

	public String salvarProjeto(ProjetoEntity projeto) {
		System.out.println("Projeto - Camada DAO - Data Access Object...");
		System.out.println("Projeto - Cadastrando no banco de dados...");
		
		String sql = "INSERT INTO PROJETO (TIPO_PROJETO, AMB_PROJETO, AREA_PROJETO, VALOR_PROJETO) VALUES (?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			ps.setString(1, projeto.getTipoProjeto());
			ps.setString(2, projeto.getAmbiente());
			ps.setDouble(3, projeto.getArea());
			ps.setDouble(4, projeto.getValor());
//			ps.setObject(5, projeto.getCliente());
//			ps.setObject(6, projeto.getFuncionario());
			
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
		
		
		//TODO implementar cadastro dessa informação no banco de dados
		return "Projeto cadastrado.";
	}
}

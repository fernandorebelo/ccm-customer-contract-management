package br.com.fertech.ccm.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ProjetoDAO {
	
	public ProjetoEntity buscarProjetoPorId(long codigoProjeto) throws BusinessException{
		String sql = "SELECT ID_PROJETO, TIPO_PROJETO, AMB_PROJETO, AREA_PROJETO, VALOR_PROJETO FROM PROJETO WHERE ID_PROJETO = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setLong(1, codigoProjeto);
			
			rs = ps.executeQuery();
			
			ProjetoEntity projetoEncontrado = null;
			
			if(rs.next()) {
				projetoEncontrado.setCodigo(rs.getLong("ID_CLIENTE"));
				projetoEncontrado.setTipoProjeto(rs.getString("TIPO_PROJETO"));
				projetoEncontrado.setAmbiente(rs.getString("AMB_PROJETO"));
				projetoEncontrado.setArea(rs.getDouble("AREA_PROJETO"));
				projetoEncontrado.setValor(rs.getDouble("VALOR_PROJETO"));
			}
			return projetoEncontrado;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao buscar projeto.");
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
	
	public void excluirProjeto(long projeto) throws BusinessException{
		String sql = "DELETE FROM PROJETO WHERE ID_PROJETO = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setLong(1, projeto);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao excluir projeto.");
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
	
	public List<ProjetoEntity> listarProjeto() throws BusinessException{
		List<ProjetoEntity> projetos = new ArrayList<ProjetoEntity>();
		String sql = "SELECT ID_PROJETO, TIPO_PROJETO, AMB_PROJETO, AREA_PROJETO, VALOR_PROJETO FROM PROJETO";
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ProjetoEntity projeto = new ProjetoEntity();
				projeto.setCodigo(rs.getInt("ID_PROJETO"));
				projeto.setTipoProjeto(rs.getString("TIPO_PROJETO"));
				projeto.setAmbiente(rs.getString("AMB_PROJETO"));
				projeto.setArea(rs.getDouble("AREA_PROJETO"));
				projeto.setValor(rs.getDouble("VALOR_PROJETO"));
				
				projetos.add(projeto);
			}
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao listar projetos.");
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
		return projetos;
	}

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
		return "Projeto cadastrado.";
	}
}

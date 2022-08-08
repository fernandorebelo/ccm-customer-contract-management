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
	
	//Filtrar de forma dinâmica
	public List<ProjetoEntity> buscarProjetoFiltrado(ProjetoEntity projeto) throws BusinessException{
		String sql = "SELECT ID_PROJETO, TIPO_PROJETO, AMB_PROJETO, AREA_PROJETO, VALOR_PROJETO FROM PROJETO";
		
		boolean adicionaWhere = true;

		List<ProjetoEntity> resultado = new ArrayList<ProjetoEntity>();
		
		if(projeto != null) {
			if(projeto.getCodigo() != null) {
				sql += " WHERE ";
				sql += " ID_PROJETO = ? ";
				adicionaWhere = false;
			}
			if(projeto.getTipoProjeto() != null && !projeto.getTipoProjeto().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " TIPO_PROJETO LIKE ?";
			}
			if(projeto.getAmbiente() != null && !projeto.getAmbiente().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " AMB_PROJETO LIKE ?";
			}
			if(projeto.getArea() != null && !projeto.getArea().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " AREA_PROJETO LIKE ?";
			}
			if(projeto.getValor() != null && !projeto.getValor().equals("")) {
				if(adicionaWhere) {
					sql += " WHERE ";
					adicionaWhere = false;
				}else {
					sql += " AND ";
				}
				sql += " VALOR_PROJETO LIKE ?";
			}
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			int indice = 0;
			if(projeto != null) {
				if(projeto.getCodigo() != null) {
					indice += 1;
					ps.setLong(indice, projeto.getCodigo());
				}
				if(projeto.getTipoProjeto() != null && !projeto.getTipoProjeto().equals("")) {
					indice += 1;
					ps.setString(indice, "%"+projeto.getTipoProjeto()+"%");
				}
				if(projeto.getAmbiente() != null && !projeto.getAmbiente().equals("")) {
					indice += 1;
					ps.setString(indice, "%"+projeto.getAmbiente()+"%");
				}
				if(projeto.getArea() != null && !projeto.getArea().equals("")) {
					indice += 1;
					ps.setString(indice, "%"+projeto.getArea()+"%");
				}
				if(projeto.getValor() != null && !projeto.getValor().equals("")) {
					indice += 1;
					ps.setString(indice, "%"+projeto.getValor()+"%");
				}
			}
			
			rs = ps.executeQuery();

			while(rs.next()) {
				ProjetoEntity projetoResultado = new ProjetoEntity();
				projetoResultado.setCodigo(rs.getLong("ID_PROJETO"));
				projetoResultado.setTipoProjeto(rs.getString("TIPO_PROJETO"));
				projetoResultado.setAmbiente(rs.getString("AMB_PROJETO"));
				projetoResultado.setArea(rs.getDouble("AREA_PROJETO"));
				projetoResultado.setValor(rs.getDouble("VALOR_PROJETO"));
				resultado.add(projetoResultado);
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
	public String alterarProjeto(ProjetoEntity projeto) throws BusinessException{
		String sql = "UPDATE PROJETO SET TIPO_PROJETO = ?, AMB_PROJETO = ?, AREA_PROJETO = ?, VALOR_PROJETO = ? WHERE ID_PROJETO = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setString(1, projeto.getTipoProjeto());
			ps.setString(2, projeto.getAmbiente());
			ps.setDouble(3, projeto.getArea());
			ps.setDouble(4, projeto.getValor());
			ps.setLong(5, projeto.getCodigo());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao atualizar os dados do projeto.");
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
		
		return "Projeto alterado com sucesso";
	}
	
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
				projetoEncontrado = new ProjetoEntity();
				projetoEncontrado.setCodigo(rs.getLong("ID_PROJETO"));
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
				projeto.setCodigo(rs.getLong("ID_PROJETO"));
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

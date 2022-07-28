package br.com.fertech.ccm.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fertech.ccm.core.dao.connection.ConnectionMySQL;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class FuncionarioDAO {
	
	public String alterarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		String sql = "UPDATE FUNCIONARIO SET NOME_FUNCIONARIO=?, CARGO_FUNCIONARIO=?, REGISTRO_FUNCIONARIO=? WHERE ID_FUNCIONARIO=?";
		PreparedStatement ps = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCargo());
			ps.setString(3, funcionario.getRegistroProfissional());
			ps.setLong(4, funcionario.getCodigoFuncionario());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao atualizar os dados do funcionário.");
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
		
		return "Funcionário alterado com sucesso";
	}
	
	public FuncionarioEntity buscarFuncionarioPorId(long codigoFuncionario) throws BusinessException{
		String sql = "SELECT ID_FUNCIONARIO, NOME_FUNCIONARIO, CARGO_FUNCIONARIO, REGISTRO_FUNCIONARIO FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			ps.setLong(1, codigoFuncionario);
			rs = ps.executeQuery();
			FuncionarioEntity funcionarioEncontrado = null;
			
			if(rs.next()) {
				funcionarioEncontrado = new FuncionarioEntity();
				funcionarioEncontrado.setCodigoFuncionario(rs.getLong("ID_FUNCIONARIO"));
				funcionarioEncontrado.setNome(rs.getString("NOME_FUNCIONARIO"));
				funcionarioEncontrado.setCargo(rs.getString("CARGO_FUNCIONARIO"));
				funcionarioEncontrado.setRegistroProfissional(rs.getString("REGISTRO_FUNCIONARIO"));
			}
			return funcionarioEncontrado;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao buscar funcionário.");
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
	
	public void excluirFuncionario(long funcionario) throws BusinessException{
		
		String sql = "DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
		
		PreparedStatement ps = null;
		
		try {
			// conexão e criar prepare statement
			ps = ConnectionMySQL.getConnection().prepareStatement(sql);
			
			//setar valores
			ps.setLong(1, funcionario);
			
			// executar comando
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ao excluir funcionario.");
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

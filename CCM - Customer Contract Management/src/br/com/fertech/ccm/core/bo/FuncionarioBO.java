package br.com.fertech.ccm.core.bo;

import java.util.List;

import br.com.fertech.ccm.core.dao.FuncionarioDAO;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.entity.UsuarioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class FuncionarioBO {
	
	public List<FuncionarioEntity> buscarFuncionarioFiltrado(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioDAO().buscarFuncionarioFiltrado(funcionario);
	}
	
	public String alterarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioDAO().alterarFuncionario(funcionario);
	}
	
	public FuncionarioEntity buscarFuncionarioPorId(long codigoFuncionario) throws BusinessException{
		return new FuncionarioDAO().buscarFuncionarioPorId(codigoFuncionario);
	}
	
	public void excluirFuncionario(long funcionario) throws BusinessException{
		new FuncionarioDAO().excluirFuncionario(funcionario);
	}
	
	public List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		return new FuncionarioDAO().listarFuncionario();
	}

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException {
		System.out.println("Funcionário - Camada BO - Business Object...");
		
		//VALIDAÇÕES FuncionarioEntity
		//TODO validar nome
		if(funcionario.getNome() != null && funcionario.getNome().equals("")) {
			throw new BusinessException("Nome precisa ser preenchido.");
		}
		
		//TODO validar cargo
		if(funcionario.getCargo() != null && funcionario.getCargo().equals("")) {
			throw new BusinessException("Cargo precisa ser preenchido.");
		}
		
		//TODO validar registro profissional crea ou cau
		if(funcionario.getRegistroProfissional() != null && funcionario.getRegistroProfissional().equals("")) {
			throw new BusinessException("Cargo precisa ser preenchido.");
		}
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		return fdao.salvarFuncionario(funcionario);
	}
	
	
	
	// MÉTODOS LOGIN FUNCIONARIO - AUTENTICAR E SALVAR
	public boolean autenticarLoginFuncionario(String login, String senha) throws BusinessException{
		return new FuncionarioDAO().autenticarLoginFuncionario(login, senha);
	}
	
	public String salvarLoginFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		
		//TODO autenticar senha
		if(funcionario.getLogin() != null && funcionario.getLogin().equals("")) {
			throw new BusinessException("Login precisa ser preenchido.");
		}
		//TODO autenticar login
		if(funcionario.getSenha() != null && funcionario.getSenha().equals("")) {
			throw new BusinessException("Senha precisa ser preenchida.");
		}
		
		return new FuncionarioDAO().salvarLoginFuncionario(funcionario);
	}
}

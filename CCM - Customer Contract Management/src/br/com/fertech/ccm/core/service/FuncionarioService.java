package br.com.fertech.ccm.core.service;

import java.util.List;

import br.com.fertech.ccm.core.bo.FuncionarioBO;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.entity.UsuarioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class FuncionarioService {
	
	public List<FuncionarioEntity> buscarFuncionarioFiltrado(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioBO().buscarFuncionarioFiltrado(funcionario);
	}
	
	public String alterarFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioBO().alterarFuncionario(funcionario);
	}
	
	public FuncionarioEntity buscarFuncionarioPorId(long codigoFuncionario) throws BusinessException{
		return new FuncionarioBO().buscarFuncionarioPorId(codigoFuncionario);
	}
	
	public void excluirFuncionario(long funcionario) throws BusinessException{
		new FuncionarioBO().excluirFuncionario(funcionario);
	}
	
	public List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		return new FuncionarioBO().listarFuncionario();
	}

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException {
		System.out.println("Funcionário - Entrando no backend... \nFuncionário - Camada service...");
		FuncionarioBO fbo = new FuncionarioBO();
		return fbo.salvarFuncionario(funcionario);
	}
	
	
	
	// MÉTODOS LOGIN FUNCIONARIO - AUTENTICAR E SALVAR
	
	public boolean autenticarLoginFuncionario(String login, String senha) throws BusinessException{
		return new FuncionarioBO().autenticarLoginFuncionario(login, senha);
	}

	public String salvarLoginFuncionario(FuncionarioEntity funcionario) throws BusinessException{
		return new FuncionarioBO().salvarLoginFuncionario(funcionario);
	}
}

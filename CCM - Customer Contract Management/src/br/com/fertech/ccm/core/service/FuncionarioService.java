package br.com.fertech.ccm.core.service;

import br.com.fertech.ccm.core.bo.FuncionarioBO;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class FuncionarioService {

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException {
		System.out.println("Funcion�rio - Entrando no backend... \nFuncion�rio - Camada service...");
		FuncionarioBO fbo = new FuncionarioBO();
		return fbo.salvarFuncionario(funcionario);
	}
}

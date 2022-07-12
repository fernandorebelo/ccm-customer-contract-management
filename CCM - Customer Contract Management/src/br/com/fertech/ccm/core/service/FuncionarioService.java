package br.com.fertech.ccm.core.service;

import br.com.fertech.ccm.core.bo.FuncionarioBO;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;

public class FuncionarioService {

	public String salvarFuncionario(FuncionarioEntity funcionario) {
		FuncionarioBO fbo = new FuncionarioBO();
		return fbo.salvarFuncionario(funcionario);
	}
}

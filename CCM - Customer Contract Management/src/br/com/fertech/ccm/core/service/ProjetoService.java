package br.com.fertech.ccm.core.service;

import br.com.fertech.ccm.core.bo.ProjetoBO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ProjetoService {

	public String salvarProjeto(ProjetoEntity projeto) throws BusinessException {
		System.out.println("Projeto - Entrando no backend... \nProjeto - Camada service...");
		ProjetoBO pbo = new ProjetoBO();
		return pbo.salvarProjeto(projeto);
	}
}

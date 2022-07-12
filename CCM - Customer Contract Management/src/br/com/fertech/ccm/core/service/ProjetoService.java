package br.com.fertech.ccm.core.service;

import br.com.fertech.ccm.core.bo.ProjetoBO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;

public class ProjetoService {

	public String salvarProjeto(ProjetoEntity projeto) {
		System.out.println("Projeto - Entrando no backend... \nProjeto - Camada service...");
		ProjetoBO pbo = new ProjetoBO();
		return pbo.salvarProjeto(projeto);
	}
}

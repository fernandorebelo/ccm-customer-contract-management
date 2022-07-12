package br.com.fertech.ccm.core.bo;

import br.com.fertech.ccm.core.dao.ProjetoDAO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;

public class ProjetoBO {

	public String salvarProjeto(ProjetoEntity projeto) {
		System.out.println("Projeto - Camada BO - Business Object...");

		//VALIDAÇÕES ProjetoEntity
		//TODO validar tipo de projeto
		//TODO validar tipo de ambiente
		//TODO validar área do ambiente
		//TODO validar valor do projeto
		//TODO validar se cliente está cadastrado
		//TODO validar se funcionário está cadastrado
		
		ProjetoDAO pdao = new ProjetoDAO();
		return pdao.salvarProjeto(projeto);
	}
}

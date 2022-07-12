package br.com.fertech.ccm.core.bo;

import br.com.fertech.ccm.core.dao.ProjetoDAO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;

public class ProjetoBO {

	public String salvarProjeto(ProjetoEntity projeto) {
		System.out.println("Projeto - Camada BO - Business Object...");

		//VALIDA��ES ProjetoEntity
		//TODO validar tipo de projeto
		//TODO validar tipo de ambiente
		//TODO validar �rea do ambiente
		//TODO validar valor do projeto
		//TODO validar se cliente est� cadastrado
		//TODO validar se funcion�rio est� cadastrado
		
		ProjetoDAO pdao = new ProjetoDAO();
		return pdao.salvarProjeto(projeto);
	}
}

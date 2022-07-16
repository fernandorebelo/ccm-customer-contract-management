package br.com.fertech.ccm.core.bo;

import br.com.fertech.ccm.core.dao.ProjetoDAO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ProjetoBO {

	public String salvarProjeto(ProjetoEntity projeto) throws BusinessException {
		System.out.println("Projeto - Camada BO - Business Object...");

		//VALIDA��ES ProjetoEntity
		//TODO validar tipo de projeto
		if(projeto.getTipoProjeto() != null && projeto.getTipoProjeto().equals("")) {
			throw new BusinessException("Projeto precisa ser preenchido.");
		}
		
		//TODO validar tipo de ambiente
		if(projeto.getAmbiente() != null && projeto.getAmbiente().equals("")) {
			throw new BusinessException("Ambiente precisa ser preenchido.");
		}
		
		//TODO validar �rea do ambiente
		if(projeto.getArea() < 0.0) {
			throw new BusinessException("�rea precisa ser maior que 0.");
		}
		
		//TODO validar valor do projeto
		if(projeto.getValor() < 0.0) {
			throw new BusinessException("Valor precisa ser maior que 0.");
		}
		
		//TODO validar se cliente est� cadastrado
		//TODO validar se funcion�rio est� cadastrado
		
		ProjetoDAO pdao = new ProjetoDAO();
		return pdao.salvarProjeto(projeto);
	}
}

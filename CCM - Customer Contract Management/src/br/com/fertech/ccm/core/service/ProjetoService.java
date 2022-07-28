package br.com.fertech.ccm.core.service;

import java.util.List;

import br.com.fertech.ccm.core.bo.ProjetoBO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ProjetoService {
	
	public String alterarProjeto(ProjetoEntity projeto) throws BusinessException{
		return new ProjetoBO().alterarProjeto(projeto);
	}
	
	public ProjetoEntity buscarProjetoPorId(long codigoProjeto) throws BusinessException{
		return new ProjetoBO().buscarProjetoPorId(codigoProjeto);
	}
	
	public void excluirProjeto(long projeto) throws BusinessException{
		new ProjetoBO().excluirProjeto(projeto);
	}
	
	public List<ProjetoEntity> listarProjeto() throws BusinessException{
		return new ProjetoBO().listarProjeto();
	}

	public String salvarProjeto(ProjetoEntity projeto) throws BusinessException {
		System.out.println("Projeto - Entrando no backend... \nProjeto - Camada service...");
		ProjetoBO pbo = new ProjetoBO();
		return pbo.salvarProjeto(projeto);
	}
}

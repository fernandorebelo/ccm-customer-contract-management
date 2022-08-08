package br.com.fertech.ccm.core.bo;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.fertech.ccm.core.dao.ClienteDAO;
import br.com.fertech.ccm.core.dao.ProjetoDAO;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ProjetoBO {
	
	public List<ProjetoEntity> buscarProjetoFiltrado(ProjetoEntity projeto) throws BusinessException{
		return new ProjetoDAO().buscarProjetoFiltrado(projeto);
	}
	
	public String alterarProjeto(ProjetoEntity projeto) throws BusinessException{
		return new ProjetoDAO().alterarProjeto(projeto);
	}
	
	public ProjetoEntity buscarProjetoPorId(long codigoProjeto) throws BusinessException{
		return new ProjetoDAO().buscarProjetoPorId(codigoProjeto);
	}
	
	public void excluirProjeto(long projeto) throws BusinessException{
		new ProjetoDAO().excluirProjeto(projeto);
	}
	
	public List<ProjetoEntity> listarProjeto() throws BusinessException{
		return new ProjetoDAO().listarProjeto();
	}

	public String salvarProjeto(ProjetoEntity projeto) throws BusinessException {
		System.out.println("Projeto - Camada BO - Business Object...");

		//VALIDAÇÕES ProjetoEntity
		//TODO validar tipo de projeto
		if(projeto.getTipoProjeto() != null && projeto.getTipoProjeto().equals("")) {
			throw new BusinessException("Projeto precisa ser preenchido.");
		}
		
		//TODO validar tipo de ambiente
		if(projeto.getAmbiente() != null && projeto.getAmbiente().equals("")) {
			throw new BusinessException("Ambiente precisa ser preenchido.");
		}
		
		//TODO validar área do ambiente
		if(projeto.getArea() < 0.0) {
			throw new BusinessException("Área precisa ser maior que 0.");
		}
		
		//TODO validar valor do projeto
		if(projeto.getValor() < 0.0) {
			throw new BusinessException("Valor precisa ser maior que 0.");
		}
		
		//TODO validar se cliente está cadastrado
		//TODO validar se funcionário está cadastrado
		
		ProjetoDAO pdao = new ProjetoDAO();
		return pdao.salvarProjeto(projeto);
	}
}

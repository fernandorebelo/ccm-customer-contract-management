package br.com.fertech.ccm.core.bo;

import java.util.List;

import br.com.fertech.ccm.core.dao.FuncionarioDAO;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class FuncionarioBO {
	
	public List<FuncionarioEntity> listarFuncionario() throws BusinessException{
		return new FuncionarioDAO().listarFuncionario();
	}

	public String salvarFuncionario(FuncionarioEntity funcionario) throws BusinessException {
		System.out.println("Funcionário - Camada BO - Business Object...");
		
		//VALIDAÇÕES FuncionarioEntity
		//TODO validar nome
		if(funcionario.getNome() != null && funcionario.getNome().equals("")) {
			throw new BusinessException("Nome precisa ser preenchido.");
		}
		
		//TODO validar cargo
		if(funcionario.getCargo() != null && funcionario.getCargo().equals("")) {
			throw new BusinessException("Cargo precisa ser preenchido.");
		}
		
		//TODO validar registro profissional crea ou cau
		if(funcionario.getRegistroProfissional() != null && funcionario.getRegistroProfissional().equals("")) {
			throw new BusinessException("Cargo precisa ser preenchido.");
		}
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		return fdao.salvarFuncionario(funcionario);
	}
}

package br.com.fertech.ccm.core.bo;

import br.com.fertech.ccm.core.dao.FuncionarioDAO;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;

public class FuncionarioBO {

	public String salvarFuncionario(FuncionarioEntity funcionario) {
		
		//VALIDAÇÕES FuncionarioEntity
		//TODO validar nome
		//TODO validar cargo
		//TODO validar registro profissional crea ou cau
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		return fdao.salvarFuncionario(funcionario);
	}
}

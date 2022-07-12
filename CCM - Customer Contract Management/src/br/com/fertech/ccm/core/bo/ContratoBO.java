package br.com.fertech.ccm.core.bo;

import br.com.fertech.ccm.core.dao.ContratoDAO;
import br.com.fertech.ccm.core.entity.ContratoEntity;

public class ContratoBO {

	public String salvarContrato(ContratoEntity contrato) {
		
		//VALIDAÇÕES ContratoEntity
		//TODO validar se cliente está cadastrado
		//TODO validar se projeto está cadastrado
		//TODO validar se funcionario está cadastrado
		
		ContratoDAO contratoDao = new ContratoDAO();
		return contratoDao.salvarContrato(contrato);
	}
}

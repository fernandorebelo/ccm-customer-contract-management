package br.com.fertech.ccm.core.bo;

import br.com.fertech.ccm.core.dao.ContratoDAO;
import br.com.fertech.ccm.core.entity.ContratoEntity;

public class ContratoBO {

	public String salvarContrato(ContratoEntity contrato) {
		
		//VALIDA��ES ContratoEntity
		//TODO validar se cliente est� cadastrado
		//TODO validar se projeto est� cadastrado
		//TODO validar se funcionario est� cadastrado
		
		ContratoDAO contratoDao = new ContratoDAO();
		return contratoDao.salvarContrato(contrato);
	}
}

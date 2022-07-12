package br.com.fertech.ccm.core.service;

import br.com.fertech.ccm.core.bo.ContratoBO;
import br.com.fertech.ccm.core.entity.ContratoEntity;

public class ContratoService {

	public String salvarContrato(ContratoEntity contrato) {
		ContratoBO contratoBo = new ContratoBO();
		return contratoBo.salvarContrato(contrato);
	}
}

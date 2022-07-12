package br.com.fertech.ccm.core.dao;

import br.com.fertech.ccm.core.entity.ContratoEntity;

public class ContratoDAO {

	public String salvarContrato(ContratoEntity contrato) {
		System.out.println("Cadastrando no banco de dados...");
		//TODO implementar cadastro dessa informação no banco de dados
		return "Contrato cadastrado.";
	}
}

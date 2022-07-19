package br.com.fertech.ccm.core.service;

import java.util.List;

import br.com.fertech.ccm.core.bo.ClienteBO;
import br.com.fertech.ccm.core.dao.ClienteDAO;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.EscritorioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ClienteService {
	
	public List<ClienteEntity> listarCliente() throws BusinessException{
		return new ClienteBO().listarCliente();
	}

	public String salvarCliente(ClienteEntity cliente) throws BusinessException {
		System.out.println("Cliente - Entrando no backend... \nCliente - Camada service...");
		ClienteBO cbo = new ClienteBO();
		return cbo.salvarCliente(cliente);
	}
	
}

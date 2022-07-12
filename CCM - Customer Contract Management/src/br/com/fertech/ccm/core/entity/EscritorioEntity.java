package br.com.fertech.ccm.core.entity;

import java.util.ArrayList;
import java.util.List;

public class EscritorioEntity {

	private List<ClienteEntity> clientes;
	private List<ProjetoEntity> projetos;
	private List<ContratoEntity> contratos;
	private List<FuncionarioEntity> funcionarios;
	
	public List<FuncionarioEntity> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioEntity> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<ClienteEntity> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteEntity> clientes) {
		this.clientes = clientes;
	}
	public List<ProjetoEntity> getProjetos() {
		return projetos;
	}
	public void setProjetos(List<ProjetoEntity> projetos) {
		this.projetos = projetos;
	}
	public List<ContratoEntity> getContratos() {
		return contratos;
	}
	public void setContratos(List<ContratoEntity> contratos) {
		this.contratos = contratos;
	}
	
}

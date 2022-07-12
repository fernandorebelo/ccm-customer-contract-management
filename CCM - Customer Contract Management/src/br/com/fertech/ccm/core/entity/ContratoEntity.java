package br.com.fertech.ccm.core.entity;

import java.util.Objects;

public class ContratoEntity {

	private long codigoContrato;
	private ClienteEntity cliente;
	private ProjetoEntity projeto;
	private FuncionarioEntity funcionario;
	
	public long getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(long codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public ProjetoEntity getProjeto() {
		return projeto;
	}
	public void setProjeto(ProjetoEntity projeto) {
		this.projeto = projeto;
	}
	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoContrato);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratoEntity other = (ContratoEntity) obj;
		return codigoContrato == other.codigoContrato;
	}
	
}

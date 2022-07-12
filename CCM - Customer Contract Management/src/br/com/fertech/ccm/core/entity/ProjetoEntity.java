package br.com.fertech.ccm.core.entity;

import java.util.Objects;

public class ProjetoEntity {

	private long codigoProjeto;
	private String tipoProjeto;
	private String ambiente;
	private double area;
	private double valor;
	private ClienteEntity cliente;
	private FuncionarioEntity funcionario;
	
	public long getCodigo() {
		return codigoProjeto;
	}
	public void setCodigo(long codigoProjeto) {
		this.codigoProjeto = codigoProjeto;
	}
	public String getTipoProjeto() {
		return tipoProjeto;
	}
	public void setTipoProjeto(String tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoProjeto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoEntity other = (ProjetoEntity) obj;
		return codigoProjeto == other.codigoProjeto;
	}
	
}

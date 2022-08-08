package br.com.fertech.ccm.core.entity;

import java.util.Objects;

public class ProjetoEntity {

	private Long codigoProjeto;
	private String tipoProjeto;
	private String ambiente;
	private Double area;
	private Double valor;
	private ClienteEntity cliente;
	private FuncionarioEntity funcionario;
	
	public Long getCodigo() {
		return codigoProjeto;
	}
	public void setCodigo(Long codigoProjeto) {
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
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
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

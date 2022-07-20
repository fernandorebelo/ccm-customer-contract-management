package br.com.fertech.ccm.core.entity;

import java.util.Objects;

public class FuncionarioEntity {

	private long codigoFuncionario;
	private String nome;
	private String cargo;
	private String registroProfissional;
	
	public long getCodigoFuncionario() {
		return codigoFuncionario;
	}
	public void setCodigoFuncionario(long codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
	public String getRegistroProfissional() {
		return registroProfissional;
	}
	public void setRegistroProfissional(String registroProfissional) {
		this.registroProfissional = registroProfissional;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoFuncionario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioEntity other = (FuncionarioEntity) obj;
		return codigoFuncionario == other.codigoFuncionario;
	}
	
}

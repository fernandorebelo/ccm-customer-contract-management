package br.com.fertech.ccm.core.dao;

import br.com.fertech.ccm.core.entity.FuncionarioEntity;

public class FuncionarioDAO {

	public String salvarFuncionario(FuncionarioEntity funcionario) {
		System.out.println("Cadastrando no banco de dados...");
		//TODO implementar cadastro dessa informação no banco de dados
		return "Funcionário cadastrado.";
	}
}

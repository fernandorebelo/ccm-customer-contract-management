package br.com.fertech.ccm.view;

import javax.swing.JOptionPane;

import br.com.fertech.ccm.core.bo.ClienteBO;
import br.com.fertech.ccm.core.dao.ClienteDAO;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.EscritorioEntity;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.EscritorioService;
import br.com.fertech.ccm.core.service.ProjetoService;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class Principal {

	public static void main(String[] args){
		//montando objeto com as informações da tela
		ClienteEntity cliente1 = new ClienteEntity();
		cliente1.setCodigoCliente(123);
		cliente1.setNome("Fernando Rebelo");
		cliente1.setCpf("111.111.111-11");
		cliente1.setEndereco("Rua Milo");
		cliente1.setTelefone("48988887777");
		cliente1.setEmail("fernando@fernando.com.br");
		
		ClienteEntity cliente2 = new ClienteEntity();
		cliente2.setCodigoCliente(123);
		cliente2.setNome("Jéssica Bett");
		cliente2.setCpf("06826662973");
		cliente2.setEndereco("Rua Amora");
		cliente2.setTelefone("(48) 944442222");
		cliente2.setEmail("jessica@jessica.com.br");
		
		ProjetoEntity projeto1 = new ProjetoEntity();
		projeto1.setTipoProjeto("Projeto de interiores");
		projeto1.setAmbiente("sala e cozinha");
		projeto1.setArea(30.0);
		projeto1.setValor(3000.0);
		projeto1.setCliente(cliente1);
		projeto1.setFuncionario(null);
		
		//chamando core/backend para salvar o cliente		
//		ClienteService cs = new ClienteService();
//		try {
//			System.out.println(cs.salvarCliente(cliente1));
//			System.out.println(cs.salvarCliente(cliente2));
//		} catch (BusinessException e) {
//			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
//		}
		
		//validar telefone teste
//		ClienteBO cbo = new ClienteBO();
//		System.out.println(cbo.validarTelefone(cliente2.getTelefone()));
		
		//validar cpf teste
//		System.out.println(cbo.validarCpf(cliente2.getCpf()));
		
		
//		ProjetoService ps = new ProjetoService();
//		System.out.println(ps.salvarProjeto(projeto1));

	}

}

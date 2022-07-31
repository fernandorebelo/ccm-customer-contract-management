package br.com.fertech.ccm.view;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.fertech.ccm.core.bo.ClienteBO;
import br.com.fertech.ccm.core.dao.ClienteDAO;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.FuncionarioEntity;
import br.com.fertech.ccm.core.entity.ProjetoEntity;
import br.com.fertech.ccm.core.service.ClienteService;
import br.com.fertech.ccm.core.service.FuncionarioService;
import br.com.fertech.ccm.core.service.ProjetoService;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class PrincipalTeste {

	public static void main(String[] args) throws BusinessException{
		//montando objeto com as informações da tela
//		ClienteEntity cliente1 = new ClienteEntity();
//		cliente1.setNome("Amora");
//		cliente1.setCpf("111.111.111-11");
//		cliente1.setEndereco("Rua Amora");
//		cliente1.setTelefone("48988887777");
//		cliente1.setEmail("amora@amora.com.br");
		
//		ClienteEntity cliente2 = new ClienteEntity();
//		cliente2.setNome("Jéssica Bett");
//		cliente2.setCpf("06826662973");
//		cliente2.setEndereco("Rua Amora");
//		cliente2.setTelefone("(48) 944442222");
//		cliente2.setEmail("jessica@jessica.com.br");
//		
//		ProjetoEntity projeto1 = new ProjetoEntity();
//		projeto1.setTipoProjeto("Projeto de interiores");
//		projeto1.setAmbiente("sala e cozinha");
//		projeto1.setArea(30.0);
//		projeto1.setValor(3000.0);
//		projeto1.setCliente(cliente1);
//		projeto1.setFuncionario(null);
		
//		FuncionarioEntity fent = new FuncionarioEntity();
//		fent.setNome("Julia");
//		fent.setCargo("Projetista");
//		fent.setRegistroProfissional("CAU A123123");
		
		
		//chamando core/backend para salvar o FUNCIONARIO
//		FuncionarioService fserv = new FuncionarioService();
//		try {
//			fserv.salvarFuncionario(fent);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
//		}
//		List<ClienteEntity> clientes = new ClienteService().listarCliente();
//		for (ClienteEntity clienteEntity : clientes) {
//			System.out.println("Nome: " + clienteEntity.getNome() + "\nCódigo: " + clienteEntity.getCodigoCliente());
//		}
		
		
		
		//buscar cliente
//		ClienteEntity clienteEncontrado = new ClienteService().buscarClientePorId(8);
//		
//		try {
//			if(clienteEncontrado != null) {
//				System.out.println(clienteEncontrado.getCodigoCliente()+" - " + clienteEncontrado.getNome());
//			}else {
//				System.out.println("Cliente não encontrado");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		//buscar projeto
//		ProjetoEntity projetoEncontrado = new ProjetoService().buscarProjetoPorId(5);
//		
//		try {
//			if(projetoEncontrado != null) {
//				System.out.println(projetoEncontrado.getCodigo()+" - " + projetoEncontrado.getAmbiente());
//			}else {
//				System.out.println("Projeto não encontrado");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		
		
//		//chamando core/backend para alterar o funcionário
//		FuncionarioService fs = new FuncionarioService();
//		FuncionarioEntity funcionario = new FuncionarioEntity();
//		funcionario.setCodigoFuncionario(6L);
//		funcionario.setNome("Fernando Rebelo");
//		funcionario.setCargo("Arquiteto poprietário");
//		funcionario.setRegistroProfissional("CAU 123456");
//		
//		
//		try {
//			
//			System.out.println(fs.alterarFuncionario(funcionario));
//		} catch (BusinessException e) {
//			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
//		}
		
		
		//chamando core/backend para salvar o projeto		
//		ProjetoService ps = new ProjetoService();
//		try {
//			System.out.println(ps.salvarProjeto(projeto1));
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, e.getMensagemDeErro());
//		}
		
		

	}

}

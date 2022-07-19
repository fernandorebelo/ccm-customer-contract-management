package br.com.fertech.ccm.core.bo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import br.com.fertech.ccm.core.dao.ClienteDAO;
import br.com.fertech.ccm.core.entity.ClienteEntity;
import br.com.fertech.ccm.core.entity.EscritorioEntity;
import br.com.fertech.ccm.core.util.exception.BusinessException;

public class ClienteBO {
	
	public void excluirCliente(long cliente) throws BusinessException{
		new ClienteDAO().excluirCliente(cliente);
	}
	
	public List<ClienteEntity> listarCliente() throws BusinessException{
		
		//Validações Cliente
			//TODO passar tudo pra maiúsculo
		
		return new ClienteDAO().listarCliente();
	}

	public String salvarCliente(ClienteEntity cliente) throws BusinessException {
		System.out.println("Cliente - Camada BO - Business Object...");
		
		//VALIDAÇÕES ClienteEntity
			//TODO validar nome
		if(cliente.getNome() != null && cliente.getNome().equals("")) {
			throw new BusinessException("Nome precisa ser preenchido.");
		}
			//TODO validar cpf
		if(cliente.getCpf() != null && cliente.getCpf().equals("")) {
			throw new BusinessException("O CPF precisa ser preenchido");
		}else if(validarCpf(cliente.getCpf()) != true) {
			throw new BusinessException("CPF inválido.");
		}
			
			//TODO validar endereco
		if(cliente.getEndereco() != null && cliente.getEndereco().equals("")) {
			throw new BusinessException("Endereço precisa ser preenchido.");
		}
		
			//TODO validar telefone
		if(cliente.getTelefone() != null && cliente.getTelefone().equals("")) {
			throw new BusinessException("Telefone precisa ser preenchido.");
		}else if(validarTelefone(cliente.getTelefone()) != true) {
			throw new BusinessException("Número de telefone inválido. (11 11111 1111)");
		}
		
			//TODO Validação de email
		if(cliente.getEmail() != null && cliente.getEmail().equals("")) {
			throw new BusinessException("O campo do e-mail precisa ser preenchido.");
		}else if(validarEmail(cliente.getEmail()) != true) {
			throw new BusinessException("Email em formato inválido.");
		}
		
		ClienteDAO cdao = new ClienteDAO();
		return cdao.salvarCliente(cliente);
	}
	
	private boolean validarTelefone(String telefone) {
		//retira todos os caracteres não-numéricos (incluindo espaço,tab, etc)    
		telefone = telefone.replaceAll("\\D","");
		
		//verifica se tem a qtde de numeros correta
		if (!(telefone.length() >= 10 && telefone.length() <= 11)) return false;     
		
		//Se tiver 11 caracteres, verificar se começa com 9 o celular    
		if (telefone.length() == 11 && Integer.parseInt(telefone.substring(2, 3)) != 9) return false;     
		
		//verifica se o numero foi digitado com todos os dígitos iguais    
		Pattern p = Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");    
		Matcher m = p.matcher(telefone);    if(m.find()) return false;        
		
		//DDDs validos    
		Integer[] codigosDDD = {11, 12, 13, 14, 15, 16, 17, 18, 19,        
								21, 22, 24, 27, 28, 31, 32, 33, 34,        
								35, 37, 38, 41, 42, 43, 44, 45, 46,        
								47, 48, 49, 51, 53, 54, 55, 61, 62,        
								64, 63, 65, 66, 67, 68, 69, 71, 73,        
								74, 75, 77, 79, 81, 82, 83, 84, 85,        
								86, 87, 88, 89, 91, 92, 93, 94, 95,        
								96, 97, 98, 99};    
		
		//verifica se o DDD é valido (sim, da pra verificar rsrsrs)    
		if ( java.util.Arrays.asList(codigosDDD).indexOf(Integer.parseInt(telefone.substring(0, 2))) == -1) return false;        
		
		//Se o número só tiver dez digitos não é um celular e por isso o número logo após o DDD deve ser 2, 3, 4, 5 ou 7     
		Integer[] prefixos = {2, 3, 4, 5, 7};        
		if (telefone.length() == 10 && java.util.Arrays.asList(prefixos).indexOf(Integer.parseInt(telefone.substring(2, 3))) == -1) return false; 
		
		//se passar por todas as validações acima, então está tudo certo    
		return true;
	}

	private boolean validarEmail(String email) {
	    boolean isEmailIdValid = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	            isEmailIdValid = true;
	        }
	    }
	    return isEmailIdValid;
	}

	private boolean validarCpf(String cpf) {
		boolean isCpfValid = false;
		if(cpf != null && cpf.length() > 0) {
			String expression = "(^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})$)";
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(cpf);
			if(matcher.matches()) {
				isCpfValid = true;
			}
		}
		return isCpfValid;
	}
	
	
}
package br.com.fertech.ccm.core.util.exception;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensagemDeErro;
	
	public BusinessException() {
	}
	
	public BusinessException(String excecao) {
		this.mensagemDeErro = excecao;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
}

package br.com.ekan.beneficiario.api.resources.exceptions;

public class InternalServerErrorResourceException extends AbstractResourceException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerErrorResourceException(String message) {
		super(message);
	}

}

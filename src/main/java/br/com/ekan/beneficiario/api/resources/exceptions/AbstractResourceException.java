package br.com.ekan.beneficiario.api.resources.exceptions;

public abstract class AbstractResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AbstractResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractResourceException(String message) {
		super(message);
	}

}

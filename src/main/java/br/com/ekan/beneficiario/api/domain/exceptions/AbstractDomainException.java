package br.com.ekan.beneficiario.api.domain.exceptions;

public abstract class AbstractDomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AbstractDomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractDomainException(String message) {
		super(message);
	}

}

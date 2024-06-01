package br.com.ekan.beneficiario.api.domain.exceptions;

public class InternalServerErrorDomainException extends AbstractDomainException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorDomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerErrorDomainException(String message) {
		super(message);
	}

}

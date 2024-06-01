package br.com.ekan.beneficiario.api.domain.exceptions;

public class WarningDomainException extends AbstractDomainException {

	private static final long serialVersionUID = 1L;

	public WarningDomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public WarningDomainException(String message) {
		super(message);
	}

}

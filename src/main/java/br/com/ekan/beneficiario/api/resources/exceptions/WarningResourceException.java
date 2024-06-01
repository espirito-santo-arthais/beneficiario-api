package br.com.ekan.beneficiario.api.resources.exceptions;

public class WarningResourceException extends AbstractResourceException {

	private static final long serialVersionUID = 1L;

	public WarningResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WarningResourceException(String message) {
		super(message);
	}

}

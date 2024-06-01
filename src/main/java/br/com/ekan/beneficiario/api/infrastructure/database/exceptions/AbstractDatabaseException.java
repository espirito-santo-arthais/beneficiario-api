package br.com.ekan.beneficiario.api.infrastructure.database.exceptions;

public abstract class AbstractDatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AbstractDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractDatabaseException(String message) {
		super(message);
	}

}

package br.com.ekan.beneficiario.api.infrastructure.database.exceptions;

public class InternalServerErrorDatabaseException extends AbstractDatabaseException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerErrorDatabaseException(String message) {
		super(message);
	}

}

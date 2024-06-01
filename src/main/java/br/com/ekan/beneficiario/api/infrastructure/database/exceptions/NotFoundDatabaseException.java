package br.com.ekan.beneficiario.api.infrastructure.database.exceptions;

public class NotFoundDatabaseException extends AbstractDatabaseException {

	private static final long serialVersionUID = 1L;

	public NotFoundDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundDatabaseException(String message) {
		super(message);
	}

}

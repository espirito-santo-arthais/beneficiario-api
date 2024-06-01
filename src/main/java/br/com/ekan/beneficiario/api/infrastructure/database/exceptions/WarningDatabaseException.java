package br.com.ekan.beneficiario.api.infrastructure.database.exceptions;

public class WarningDatabaseException extends AbstractDatabaseException {

	private static final long serialVersionUID = 1L;

	public WarningDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public WarningDatabaseException(String message) {
		super(message);
	}

}

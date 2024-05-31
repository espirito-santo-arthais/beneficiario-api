package br.com.ekan.beneficiario.api.resources.enums;

/**
 * Tipifica as mensagens retornandas para o cliente da API.
 */
public enum MessageTypeEnum {
	ERROR("Error", "Erro"),
	INFO("Information", "Informação"),
	WARNING("Warning", "Aviso"),
	SUCCESS("Success", "Sucesso");

	private final String label;
	private final String description;

	MessageTypeEnum(String label, String description) {
		this.label = label;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}
}

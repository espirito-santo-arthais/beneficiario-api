package br.com.ekan.beneficiario.api.domain.enums;

/**
 * Tipifica os tipos de documentos dos beneficiários.
 */
public enum DocumentTypeEnum {
	CPF("CPF", "CPF do beneficiário"),
	RG("RG", "Registro geral (carteira de identidade) do beneficiário");

	private final String label;
	private final String description;

	DocumentTypeEnum(String label, String description) {
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

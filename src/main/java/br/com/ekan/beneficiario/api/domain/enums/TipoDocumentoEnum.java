package br.com.ekan.beneficiario.api.domain.enums;

public enum TipoDocumentoEnum {
	CPF("CPF", "CPF do beneficiário"),
	RG("RG", "Registro geral (carteira de identidade) do beneficiário");

	private final String label;
	private final String description;

	TipoDocumentoEnum(String label, String description) {
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

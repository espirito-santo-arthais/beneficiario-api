package br.com.ekan.beneficiario.api.domain.models;

import org.springframework.lang.Nullable;

import br.com.ekan.beneficiario.api.domain.enums.DocumentTypeEnum;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Document extends AbstractModel {

	@Nullable
	private Beneficiary beneficiary;

	@Nullable
	private DocumentTypeEnum documentTypeEnum;

	@Nullable
	@Size(	min = 1,
			max = 100,
			message = "Deve ter entre 1 e 100 caracteres")
	private String description;

	public boolean hasUpdate() {
		// O beneficiário de um documento nunca é alterado.
		return this.documentTypeEnum != null 
				|| this.description != null 
				|| this.insertDate != null 
				|| this.updateDate != null;
	}

}

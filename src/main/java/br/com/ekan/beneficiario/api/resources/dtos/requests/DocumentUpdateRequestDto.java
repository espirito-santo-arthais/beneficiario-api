package br.com.ekan.beneficiario.api.resources.dtos.requests;

import org.springframework.lang.Nullable;

import br.com.ekan.beneficiario.api.domain.enums.DocumentTypeEnum;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DocumentUpdateRequestDto extends AbstractUpdateRequestDto {

	@Nullable
	@EqualsAndHashCode.Include
	private DocumentTypeEnum documentTypeEnum;

	@Nullable
	@Size(	min = 1,
			max = 100,
			message = "Deve ter entre 1 e 100 caracteres")
	private String description;

	public boolean hasUpdate() {
		return this.documentTypeEnum != null
				|| this.description != null;
	}

}

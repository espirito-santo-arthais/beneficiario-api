package br.com.ekan.beneficiario.api.resources.dtos.requests;

import br.com.ekan.beneficiario.api.domain.enums.DocumentTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
public class DocumentoCreateRequestDto extends AbstractCreateRequestDto {

	@NotNull(message = "Não pode ser nulo")
	private DocumentTypeEnum documentTypeEnum;

	@NotNull(message = "Não pode ser nulo")
	@Size(	min = 1,
			max = 100,
			message = "Deve ter entre 1 e 100 caracteres")
	private String descricao;

}

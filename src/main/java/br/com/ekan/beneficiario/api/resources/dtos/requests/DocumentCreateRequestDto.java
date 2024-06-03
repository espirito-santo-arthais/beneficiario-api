package br.com.ekan.beneficiario.api.resources.dtos.requests;

import java.util.UUID;

import br.com.ekan.beneficiario.api.domain.enums.DocumentTypeEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
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
public class DocumentCreateRequestDto extends AbstractCreateRequestDto {

	@Nullable
	protected UUID beneficiaryId;

	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private DocumentTypeEnum documentType;

	@NotNull(message = "Não pode ser nulo")
	@Size(min = 1, max = 100, message = "Deve ter entre 1 e 100 caracteres")
	@EqualsAndHashCode.Include
	private String description;

}

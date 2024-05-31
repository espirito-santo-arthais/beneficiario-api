package br.com.ekan.beneficiario.api.resources.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ekan.beneficiario.api.domain.enums.TipoDocumentoEnum;
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
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclui propriedades nulas na serialização
public class DocumentoResponseDto extends AbstractResponseDto {

	@NotNull(message = "Não pode ser nulo")
	private TipoDocumentoEnum internalPlatformEnum;

	@NotNull(message = "Não pode ser nulo")
	@Size(	min = 1,
			max = 100,
			message = "Deve ter entre 1 e 100 caracteres")
	private String descricao;

}

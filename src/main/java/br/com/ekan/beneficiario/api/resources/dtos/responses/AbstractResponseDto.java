package br.com.ekan.beneficiario.api.resources.dtos.responses;

import java.time.LocalDate;
import java.util.UUID;

import br.com.ekan.beneficiario.api.resources.dtos.AbstractDto;
import jakarta.validation.constraints.NotNull;
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
public abstract class AbstractResponseDto extends AbstractDto {

	@NotNull(message = "Não pode ser nulo")
	protected UUID id;

	@NotNull(message = "Não pode ser nulo")
	protected LocalDate insertDate;

	@NotNull(message = "Não pode ser nulo")
	protected LocalDate updateDate;

}
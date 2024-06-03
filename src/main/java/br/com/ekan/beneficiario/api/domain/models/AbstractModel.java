package br.com.ekan.beneficiario.api.domain.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractModel {

	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	protected UUID id;

	@NotNull(message = "Não pode ser nulo")
	protected LocalDate insertDate;

	@NotNull(message = "Não pode ser nulo")
	protected LocalDate updateDate;

}
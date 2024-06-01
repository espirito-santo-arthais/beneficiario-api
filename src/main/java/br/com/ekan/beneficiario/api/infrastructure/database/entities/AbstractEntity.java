package br.com.ekan.beneficiario.api.infrastructure.database.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public abstract class AbstractEntity {

	@Id
	@NotNull(message = "Não pode ser nulo")
	protected String id;

	@Column(name = "dataInclusao")
	@NotNull(message = "Não pode ser nulo")
	protected LocalDate insertDate;

	@Column(name = "dataAtualizacao")
	@NotNull(message = "Não pode ser nulo")
	protected LocalDate updateDate;
	
}

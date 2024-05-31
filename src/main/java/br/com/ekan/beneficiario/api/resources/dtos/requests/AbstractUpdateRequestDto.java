package br.com.ekan.beneficiario.api.resources.dtos.requests;

import java.util.Objects;
import java.util.UUID;

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
public abstract class AbstractUpdateRequestDto extends AbstractRequestDto {

	@NotNull(message = "Não pode ser nulo")
	public UUID id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj == null || getClass() != obj.getClass())
			return false;
		AbstractUpdateRequestDto other = (AbstractUpdateRequestDto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
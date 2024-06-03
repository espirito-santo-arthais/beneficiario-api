package br.com.ekan.beneficiario.api.resources.dtos.requests;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
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
public class BeneficiaryUpdateRequestDto extends AbstractUpdateRequestDto {

	@Nullable
	@Size(min = 1, max = 100, message = "Deve ter entre 1 e 100 caracteres")
	private String name;

	@Nullable
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Formato de telefone inválido")
	private String phoneNumber;

	@Nullable
	private LocalDate birthDate;

	public boolean hasUpdate() {
		return this.name != null
				|| this.phoneNumber != null
				|| this.birthDate != null;
	}

}

package br.com.ekan.beneficiario.api.resources.dtos.requests;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BeneficiaryCreateRequestDto extends AbstractCreateRequestDto {

	@NotNull(message = "Não pode ser nulo")
	@Size(min = 1, max = 100,message = "Deve ter entre 1 e 100 caracteres")
	@EqualsAndHashCode.Include
	private String name;

	@NotNull(message = "Não pode ser nulo")
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Formato de telefone inválido")
	@EqualsAndHashCode.Include
	private String phoneNumber;

	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private LocalDate birthDate;

	@NotEmpty(message = "Não pode ser nulo ou vazio")
	@Singular("document")
	@ToString.Exclude
	private List<
		@NotNull(message = "Não pode ser nulo") 
		DocumentCreateRequestDto> documentList;

}

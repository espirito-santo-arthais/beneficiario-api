package br.com.ekan.beneficiario.api.resources.dtos.responses;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclui propriedades nulas na serialização
public class BeneficiaryResponseDto extends AbstractResponseDto {

	@NotNull(message = "Não pode ser nulo")
    @Size(min = 1, max = 30, message = "Deve ter entre 1 e 30 caracteres")
	@EqualsAndHashCode.Include
    private String name;

	@NotNull(message = "Não pode ser nulo")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Formato de telefone inválido")
	@EqualsAndHashCode.Include
    private String phoneNumber;

	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private LocalDate birthDate;
	
	@Nullable
	@Singular("document")
	@ToString.Exclude
	private List<
		@NotNull(message = "Não pode ser nulo") 
		DocumentResponseDto> documentList;

}

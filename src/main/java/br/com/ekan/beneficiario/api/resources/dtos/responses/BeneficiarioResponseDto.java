package br.com.ekan.beneficiario.api.resources.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclui propriedades nulas na serialização
public class BeneficiarioResponseDto extends AbstractResponseDto {

	@NotNull(message = "Não pode ser nulo")
    @Size(min = 1, max = 30, message = "Deve ter entre 1 e 30 caracteres")
    private String name;

	@NotNull(message = "Não pode ser nulo")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Formato de telefone inválido")
    private String phoneNumber;

	@NotNull(message = "Não pode ser nulo")
	private LocalDateTime birthDate;
	
	@Nullable
	@Singular("documento")
	//@ToString.Exclude
	private List<
		@NotNull(message = "Não pode ser nulo") 
		DocumentoResponseDto> documentoList;

}

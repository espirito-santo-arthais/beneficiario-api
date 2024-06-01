package br.com.ekan.beneficiario.api.domain.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.Nullable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Beneficiary extends AbstractModel {

    @Nullable
    @Size(min = 1, max = 100, message = "Deve ter entre 1 e 30 caracteres")
	@EqualsAndHashCode.Include
    private String name;

    @Nullable
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
		Document> documentList;

    public boolean hasUpdate() {
        return this.name != null
                || this.phoneNumber != null
        		|| this.birthDate != null;
    }

}

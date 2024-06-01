package br.com.ekan.beneficiario.api.infrastructure.database.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Entidade que representa um registro na tabela de beneficiários.
 **/
@Entity
@Table(name = "Beneficiario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@SuperBuilder
public class BeneficiaryEntity extends AbstractEntity {

	@Column(name = "nome", length = 100)
	@NotNull(message = "Não pode ser nulo")
    @Size(min = 1, max = 100, message = "Deve ter entre 1 e 100 caracteres")
	@EqualsAndHashCode.Include
    private String name;

	@Column(name = "telefone", length = 15)
	@NotNull(message = "Não pode ser nulo")
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Formato de telefone inválido")
	@EqualsAndHashCode.Include
    private String phoneNumber;

	@Column(name = "dataNascimento")
	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private LocalDate birthDate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "beneficiary", orphanRemoval = true)
	@NotNull(message = "Não pode ser nulo")
	@Singular("document")
	@ToString.Exclude
	private List<
		@NotNull(message = "Não pode ser nulo") 
		DocumentEntity> documentList;

}

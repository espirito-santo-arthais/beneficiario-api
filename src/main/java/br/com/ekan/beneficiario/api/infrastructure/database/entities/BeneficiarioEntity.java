package br.com.ekan.beneficiario.api.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
public class BeneficiarioEntity extends AbstractEntity {

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

	@Column
	@Nullable
	@EqualsAndHashCode.Include
	private LocalDate birthDate;

	@Column(length = 30)
	@NotNull(message = "Não pode ser nulo")
    @Size(min = 1, max = 30, message = "Deve ter entre 1 e 30 caracteres")
	@EqualsAndHashCode.Include
    private String lastName;

	@Column(length = 254)
	@NotNull(message = "Não pode ser nulo")
    @Email(message = "Email inválido")
	@EqualsAndHashCode.Include
    private String email;
	
	@Column
	@EqualsAndHashCode.Include
	boolean active;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programUser", orphanRemoval = false)
	@NotNull(message = "Não pode ser nulo")
	@Singular("contact")
	@ToString.Exclude
	private List<
		@NotNull(message = "Não pode ser nulo") 
		ContactEntity> contactList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programUser", orphanRemoval = false)
	@NotNull(message = "Não pode ser nulo")
	@Singular("calendar")
	@ToString.Exclude
	private List<
		@NotNull(message = "Não pode ser nulo") 
		CalendarEntity> calendarList;

}

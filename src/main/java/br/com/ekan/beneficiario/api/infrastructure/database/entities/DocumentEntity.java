package br.com.ekan.beneficiario.api.infrastructure.database.entities;

import br.com.ekan.beneficiario.api.domain.enums.DocumentTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Entidade que representa um registro na tabela de documentos.
 */
@Entity
@Table(name = "document", uniqueConstraints = {
		@UniqueConstraint(
				columnNames = {"beneficiary_id", "documentType"},
				name = "document_UK_001")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@SuperBuilder
public class DocumentEntity extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "beneficiario_id",
			foreignKey = @ForeignKey(name = "documents_fk_001"),
			nullable = false)
	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private BeneficiaryEntity beneficiary;

	@Column(name = "tipoDocumento", length = 15)
	@NotNull(message = "Não pode ser nulo")
	@Enumerated(EnumType.STRING)
	@EqualsAndHashCode.Include
	private DocumentTypeEnum documentTypeEnum;

	@Column(name = "descricao", length = 100)
	@NotNull(message = "Não pode ser nulo")
    @Size(min = 1, max = 100, message = "Deve ter entre 1 e 100 caracteres")
	@EqualsAndHashCode.Include
    private String description;

}

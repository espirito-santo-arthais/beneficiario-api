package br.com.ekan.beneficiario.api.infrastructure.database.entities;

import java.util.Map;

import br.com.grupogrowth7.calendar.api.domain.enums.InternalPlatformEnum;
import br.com.grupogrowth7.calendar.api.infrastructure.database.entities.AbstractEntity;
import br.com.grupogrowth7.calendar.api.infrastructure.database.entities.calendarizeds.calendars.CalendarEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Entidade que representa um registro da tabela de protocolos. Um protocolo
 * sempre é gerado para o atendimento de um cooperado através dos canais
 * digitais.
 */
@Entity
@Table(name = "program_user_integrations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@SuperBuilder
public class DocumentoEntity extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "calendars_id",
			foreignKey = @ForeignKey(name = "program_user_integrations_fk_001"))
	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private CalendarEntity calendar;

	@NotNull(message = "Não pode ser nulo")
	@EqualsAndHashCode.Include
	private InternalPlatformEnum internalPlatformEnum;

	/**
	 * Propriedades relativas à integração com um usuário de outro sistema interno.
	 * 
	 */
    @ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "programUser_integration_properties",
		    joinColumns = @JoinColumn(
		    		name = "programUser_integrations_id",
		    		referencedColumnName = "id",
		    		foreignKey = @ForeignKey(
		    				name = "programUser_integration_properties_fk_001")))
	@MapKeyColumn(name = "property_key", unique = true, length = 255, columnDefinition = "VARCHAR")
	@Column(name = "property_value", nullable = false, length = 4096, columnDefinition = "VARCHAR")
	@NotNull(message = "Não pode ser nulo")
	@Singular("programUserIntegrationProperty")
	@EqualsAndHashCode.Include
	private Map<
		@NotBlank(message = "Não pode ser nulo nem vazio") 
		String, 
		@NotBlank(message = "Não pode ser nulo nem vazio") 
		String> programUserIntegrationPropertyMap;

}

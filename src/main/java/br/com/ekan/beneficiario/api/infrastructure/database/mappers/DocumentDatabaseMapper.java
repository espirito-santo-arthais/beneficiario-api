package br.com.ekan.beneficiario.api.infrastructure.database.mappers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.DocumentEntity;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.InternalServerErrorDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.WarningDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DocumentDatabaseMapper implements DatabaseMapper<Document, DocumentEntity> {

	private final BeneficiaryDatabaseMapper beneficiaryDatabaseMapper;
	
	public DocumentDatabaseMapper(@Lazy BeneficiaryDatabaseMapper beneficiaryDatabaseMapper) {
		this.beneficiaryDatabaseMapper = beneficiaryDatabaseMapper;
	}
	
	@Override
	public DocumentEntity toEntity(Document model) {
	    log.info("Mapeando modelo {} para entidade {}...", Document.class.getSimpleName(), DocumentEntity.class.getSimpleName());
	    log.debug("model: {}", model);

	    return Optional.ofNullable(model)
	            .map(modelTemp -> {
	                try {
	                    DocumentEntity entity = DocumentEntity.builder()
	                            .id(modelTemp.getId().toString())
	                            .beneficiary(beneficiaryDatabaseMapper.toEntity(modelTemp.getBeneficiary()))
	                            .documentTypeEnum(modelTemp.getDocumentTypeEnum())
	                            .description(modelTemp.getDescription())
	                            .insertDate(modelTemp.getInsertDate())
	                            .updateDate(modelTemp.getUpdateDate())
	                            .build();
	                    log.info("Modelo mapeado para entity com sucesso!");
	                    log.debug("entity: {}", entity);
	                    return entity;
	                } catch (Exception ex) {
	                	String errorMessage = "Erro ao mapear modelo para entidade.";
	                    log.error(String.format("%1s. %2s", errorMessage, ex.getMessage()), ex);
	                    throw new InternalServerErrorDatabaseException(errorMessage, ex);
	                }
	            })
	            .orElseGet(() -> {
	                String warningMessage = String.format("Tentativa de mapear modelo %1s nulo para entidade %2s.", Document.class.getSimpleName(), DocumentEntity.class.getSimpleName());
	                log.warn(warningMessage);
	                throw new WarningDatabaseException(warningMessage);
	            });
	}

	@Override
	public Document toModel(DocumentEntity entity) {
	    log.info("Mapeando entidade {} para modelo {}...", DocumentEntity.class.getSimpleName(), Document.class.getSimpleName());
	    log.info("entity: {}", entity);
	    return Optional.ofNullable(entity)
	            .map(entityTemp -> {
	                try {
	                    Document model = Document.builder()
	                            .id(UUID.fromString(entityTemp.getId()))
	                            .beneficiary(beneficiaryDatabaseMapper.toModel(entityTemp.getBeneficiary()))
	                            .documentTypeEnum(entityTemp.getDocumentTypeEnum())
	                            .description(entityTemp.getDescription())
	                            .insertDate(entityTemp.getInsertDate())
	                            .updateDate(entityTemp.getUpdateDate())
	                            .build();
	                    log.info("Entidade mapeada para modelo com sucesso!");
	                    log.debug("model: {}", model);
	                    return model;
	                } catch (Exception ex) {
	                	String errorMessage = "Erro ao mapear entidade para modelo.";
	                    log.error(String.format("%1s. %2s", errorMessage, ex.getMessage()), ex);
	                    throw new InternalServerErrorDatabaseException(errorMessage, ex);
	                }
	            })
	            .orElseGet(() -> {
	                String warningMessage = String.format("Tentativa de mapear entidade %1s nula para modelo %2s.", DocumentEntity.class.getSimpleName(), Document.class.getSimpleName());
	                log.warn(warningMessage);
	                throw new WarningDatabaseException(warningMessage);
	            });
	}

	@Override
	public List<DocumentEntity> toEntityList(List<Document> models) {
	    log.info("Iniciando mapeamento de lista de modelos {} para lista de entitys {}", Document.class.getSimpleName(), DocumentEntity.class.getSimpleName());

	    try {
	        List<DocumentEntity> entities = Optional.ofNullable(models)
	            .map(list -> list.stream()
	                             .map(this::toEntity)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                String warningMessage = String.format("Tentativa de mapear lista de modelos %1s nula para lista de entidades %2s.", Document.class.getSimpleName(), DocumentEntity.class.getSimpleName());
	                log.warn(warningMessage);
	                throw new WarningDatabaseException(warningMessage);
	            });

	        log.info("Mapeamento de lista de modelos para lista de entidades concluído com sucesso!");
	        return entities;
	    } catch (Exception ex) {
	        String errorMessage = String.format("Erro ao mapear lista de modelos para lista de entidades: %1s", ex.getMessage());
	        log.error(errorMessage, ex);
	        throw new InternalServerErrorDatabaseException(errorMessage, ex);
	    }
	}

	@Override
	public List<Document> toModelList(List<DocumentEntity> entities) {
	    log.info("Iniciando mapeamento de lista de entidades {} para lista de modelos {}", DocumentEntity.class.getSimpleName(), Document.class.getSimpleName());

	    try {
	        List<Document> models = Optional.ofNullable(entities)
	            .map(list -> list.stream()
	                             .map(this::toModel)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                String warningMessage = String.format("Tentativa de mapear lista de entidades %1s nula para lista de modelos %2s.", DocumentEntity.class.getSimpleName(), Document.class.getSimpleName());
	                log.warn(warningMessage);
	                throw new WarningDatabaseException(warningMessage);
	            });

	        log.info("Mapeamento de lista de entidades para lista de modelos concluído com sucesso!");
	        return models;
	    } catch (Exception ex) {
	        String errorMessage = String.format("Erro ao mapear lista de entidades para lista de modelos: %1s", ex.getMessage());
	        log.error(errorMessage, ex);
	        throw new InternalServerErrorDatabaseException(errorMessage, ex);
	    }
	}
	
}

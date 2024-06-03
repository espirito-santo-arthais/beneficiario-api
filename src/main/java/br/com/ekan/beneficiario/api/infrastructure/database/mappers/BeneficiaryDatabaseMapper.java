package br.com.ekan.beneficiario.api.infrastructure.database.mappers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.BeneficiaryEntity;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.InternalServerErrorDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.WarningDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeneficiaryDatabaseMapper implements DatabaseMapper<Beneficiary, BeneficiaryEntity> {
	
	@Override
	public BeneficiaryEntity toEntity(Beneficiary model) {
	    log.info("Mapeando modelo {} para entidade {}...", Beneficiary.class.getSimpleName(), BeneficiaryEntity.class.getSimpleName());
	    log.debug("model: {}", model);

	    return Optional.ofNullable(model)
	            .map(modelTemp -> {
	                try {
	                    BeneficiaryEntity entity = BeneficiaryEntity.builder()
	                            .id(modelTemp.getId().toString())
	                            .name(modelTemp.getName())
	                            .phoneNumber(modelTemp.getPhoneNumber())
	                            .birthDate(modelTemp.getBirthDate())
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
                	String warningMessage = String.format("Tentativa de mapear modelo %1s nulo para entidade %2s.", Beneficiary.class.getSimpleName(), BeneficiaryEntity.class.getSimpleName());
	                log.warn(warningMessage);
	                throw new WarningDatabaseException(warningMessage);
	            });
	}

	@Override
	public Beneficiary toModel(BeneficiaryEntity entity) {
	    log.info("Mapeando entidade {} para modelo {}...", BeneficiaryEntity.class.getSimpleName(), Beneficiary.class.getSimpleName());
	    log.info("entity: {}", entity);
	    return Optional.ofNullable(entity)
	            .map(entityTemp -> {
	                try {
	                    Beneficiary model = Beneficiary.builder()
	                    		.id(UUID.fromString(entityTemp.getId()))
	                    		.name(entityTemp.getName())
	                            .phoneNumber(entityTemp.getPhoneNumber())
	                            .birthDate(entityTemp.getBirthDate())
	                            .insertDate(entityTemp.getInsertDate())
	                            .updateDate(entityTemp.getUpdateDate())
	                            .build();
	                    log.info("Entidade mapeada para modelo com sucesso!");
	                    log.debug("model: ", model);
	                    return model;
	                } catch (Exception ex) {
	                	String errorMessage = "Erro ao mapear entidade para modelo.";
	                    log.error(String.format("%1s. %2s", errorMessage, ex.getMessage()), ex);
	                    throw new InternalServerErrorDatabaseException(errorMessage, ex);
	                }
	            })
	            .orElseGet(() -> {
                	String warningMessage = String.format("Tentativa de mapear entidade %1s nula para modelo %2s.", BeneficiaryEntity.class.getSimpleName(), Beneficiary.class.getSimpleName());
	                log.warn(warningMessage);
	                throw new WarningDatabaseException(warningMessage);
	            });
	}

	@Override
	public List<BeneficiaryEntity> toEntityList(List<Beneficiary> models) {
	    log.info("Iniciando mapeamento de lista de modelos {} para lista de entitys {}", Beneficiary.class.getSimpleName(), BeneficiaryEntity.class.getSimpleName());

	    try {
	        List<BeneficiaryEntity> entities = Optional.ofNullable(models)
	            .map(list -> list.stream()
	                             .map(this::toEntity)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
                	String warningMessage = String.format("Tentativa de mapear lista de modelos %1s nula para lista de entidades %2s.", Beneficiary.class.getSimpleName(), BeneficiaryEntity.class.getSimpleName());
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
	public List<Beneficiary> toModelList(List<BeneficiaryEntity> entities) {
	    log.info("Iniciando mapeamento de lista de entidades {} para lista de modelos {}", BeneficiaryEntity.class.getSimpleName(), Beneficiary.class.getSimpleName());

	    try {
	        List<Beneficiary> models = Optional.ofNullable(entities)
	            .map(list -> list.stream()
	                             .map(this::toModel)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
                	String warningMessage = String.format("Tentativa de mapear lista de entidades %1s nula para lista de modelos %2s.", Beneficiary.class.getSimpleName(), BeneficiaryEntity.class.getSimpleName());
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

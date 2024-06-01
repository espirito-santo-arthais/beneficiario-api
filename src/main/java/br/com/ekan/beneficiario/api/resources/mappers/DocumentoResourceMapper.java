package br.com.ekan.beneficiario.api.resources.mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentoCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentoUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentoResponseDto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DocumentoResourceMapper
	implements ResourceMapper<
		DocumentoCreateRequestDto, 
		DocumentoUpdateRequestDto, 
		DocumentoResponseDto, 
		Document> {

	@Override
	public Document toCreationModel(DocumentoCreateRequestDto dto) {
	    log.info("Mapeando DTO {} para modelo {}...", DocumentoCreateRequestDto.class.getSimpleName(), Document.class.getSimpleName());
	    log.debug("dto: {}", dto);

	    return Optional.ofNullable(dto)
	            .map(dtoTemp -> {
	                try {
	                	LocalDate now = LocalDate.now();
	                	
	                	Document model = Document.builder()
	                            .id(UUID.randomUUID())
	                            .tipoDocumentoEnum(dtoTemp.getTipoDocumentoEnum())
	                            .descricao(dtoTemp.getDescricao())
	                            .insertDate(now)
	                            .updateDate(now)
	                    	    .build();
	                    log.info("DTO mapeado para modelo com sucesso!");
	                    log.debug("model: {}", model);
	                    return model;
	                } catch (Exception ex) {
	                    log.error(String.format("Erro ao mapear DTO para modelo. %1s", ex.getMessage()), ex);
	                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                    return null;
	                }
	            })
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear DTO {} nulo para modelo {}", DocumentoCreateRequestDto.class.getSimpleName(), Document.class.getSimpleName());
                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                return null;
	            });
	}

	@Override
	public Document toUpdateModel(DocumentoUpdateRequestDto dto) {
	    log.info("Mapeando DTO {} para modelo {}...", DocumentoUpdateRequestDto.class.getSimpleName(), Document.class.getSimpleName());
	    log.debug("dto: {}", dto);

	    return Optional.ofNullable(dto)
	            .map(dtoTemp -> {
	                try {
	                	LocalDate now = LocalDate.now();
	                	
	                	Document model = Document.builder()
	                            .id(dtoTemp.getId())
	                            .tipoDocumentoEnum(dtoTemp.getTipoDocumentoEnum())
	                            .descricao(dtoTemp.getDescricao())
	                            .updateDate(now)
	                    	    .build();
	                    log.info("DTO mapeado para modelo com sucesso!");
	                    log.debug("model: {}", model);
	                    return model;
	                } catch (Exception ex) {
	                    log.error(String.format("Erro ao mapear DTO para modelo. %1s", ex.getMessage()), ex);
	                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                    return null;
	                }
	            })
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear DTO {} nulo para modelo {}", DocumentoUpdateRequestDto.class.getSimpleName(), Document.class.getSimpleName());
                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                return null;
	            });
	}

	@Override
	public DocumentoResponseDto toResponseDto(Document model) {
	    log.info("Mapeando modelo {} para DTO {}...", Document.class.getSimpleName(), DocumentoResponseDto.class.getSimpleName());
	    log.info("model: {}", model);
	    return Optional.ofNullable(model)
	            .map(modelTemp -> {
	                try {
	                	DocumentoResponseDto dto = DocumentoResponseDto.builder()
	                    		.id(modelTemp.getId())
	                            .tipoDocumentoEnum(modelTemp.getTipoDocumentoEnum())
	                            .descricao(modelTemp.getDescricao())
	                            .insertDate(modelTemp.getInsertDate())
	                            .updateDate(modelTemp.getUpdateDate())
	                            .build();
	                    log.info("Modelo mapeado para DTO com sucesso!");
	                    log.debug("dto: ", dto);
	                    return dto;
	                } catch (Exception ex) {
	                    log.error(String.format("Erro ao mapear modelo para DTO. %1s", ex.getMessage()), ex);
	                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                    return null;
	                }
	            })
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear modelo {} nula para DTO {}", Document.class.getSimpleName(), DocumentoResponseDto.class.getSimpleName());
                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                return null;
	            });
	}

	@Override
	public List<Document> toCreationModelList(List<DocumentoCreateRequestDto> dtos) {
	    log.info("Iniciando mapeamento de lista de DTOs {} para lista de modelos {}", DocumentoCreateRequestDto.class.getSimpleName(), Document.class.getSimpleName());

	    try {
	        List<Document> models = Optional.ofNullable(dtos)
	            .map(list -> list.stream()
	                             .map(this::toCreationModel)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear lista de {} nula para lista de {}", DocumentoCreateRequestDto.class.getSimpleName(), Document.class.getSimpleName());
	                // TODO: podemos retornar uma lista vazia ou lançar uma exceção customizada
	                return Collections.emptyList();
	            });

	        log.info("Mapeamento de lista de DTOs para lista de modelos concluído com sucesso!");
	        return models;
	    } catch (Exception ex) {
	        log.error(String.format("Erro ao mapear lista de DTOs para lista de modelos. %1s", ex.getMessage()), ex);
            // TODO: podemos retornar uma lista vazia ou lançar uma exceção customizada
	        return Collections.emptyList();
	    }
	}

	@Override
	public List<Document> toUpdateModelList(List<DocumentoUpdateRequestDto> dtos) {
	    log.info("Iniciando mapeamento de lista de DTOs {} para lista de modelos {}", DocumentoUpdateRequestDto.class.getSimpleName(), Document.class.getSimpleName());

	    try {
	        List<Document> models = Optional.ofNullable(dtos)
	            .map(list -> list.stream()
	                             .map(this::toUpdateModel)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear lista de DTOs {} nula para lista de modelos {}", DocumentoUpdateRequestDto.class.getSimpleName(), Document.class.getSimpleName());
	                // TODO: podemos retornar uma lista vazia ou lançar uma exceção customizada
	                return Collections.emptyList();
	            });

	        log.info("Mapeamento de lista de DTOs para lista de modelos concluído com sucesso!");
	        return models;
	    } catch (Exception ex) {
	        log.error(String.format("Erro ao mapear lista de DTOs para lista de modelos. %1s", ex.getMessage()), ex);
            // TODO: podemos retornar uma lista vazia ou lançar uma exceção customizada
	        return Collections.emptyList();
	    }
	}

	@Override
	public List<DocumentoResponseDto> toResponseDtoList(List<Document> models) {
	    log.info("Iniciando mapeamento de lista de modelos {} para lista de DTOs {}", Document.class.getSimpleName(), DocumentoResponseDto.class.getSimpleName());

	    try {
	        List<DocumentoResponseDto> dtos = Optional.ofNullable(models)
	            .map(list -> list.stream()
	                             .map(this::toResponseDto)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear lista de modelos {} nula para lista de DTOs {}", Document.class.getSimpleName(), DocumentoResponseDto.class.getSimpleName());
	                // TODO: podemos retornar uma lista vazia ou lançar uma exceção customizada
	                return Collections.emptyList();
	            });

	        log.info("Mapeamento de lista de modelos para lista de DTOs concluído com sucesso!");
	        return dtos;
	    } catch (Exception ex) {
	        log.error(String.format("Erro ao mapear lista de modelos para lista de DTOs: %1s", ex.getMessage()), ex);
            // TODO: podemos retornar uma lista vazia ou lançar uma exceção customizada
	        return Collections.emptyList();
	    }
	}

}

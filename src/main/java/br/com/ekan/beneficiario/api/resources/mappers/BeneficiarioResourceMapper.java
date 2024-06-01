package br.com.ekan.beneficiario.api.resources.mappers;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.ekan.beneficiario.api.domain.models.Beneficiario;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiarioCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiarioUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiarioResponseDto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeneficiarioResourceMapper 
	implements ResourceMapper<
		BeneficiarioCreateRequestDto, 
		BeneficiarioUpdateRequestDto, 
		BeneficiarioResponseDto, 
		Beneficiario> {

	private final DocumentoResourceMapper documentoResourceMapper;
	
	public BeneficiarioResourceMapper(@Lazy DocumentoResourceMapper documentoResourceMapper) {
		this.documentoResourceMapper = documentoResourceMapper;
	}

	@Override
	public Beneficiario toCreationModel(BeneficiarioCreateRequestDto dto) {
	    log.info("Mapeando DTO {} para modelo {}...", BeneficiarioCreateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());
	    log.debug("dto: {}", dto);

	    return Optional.ofNullable(dto)
	            .map(dtoTemp -> {
	                try {
	                	LocalDate now = LocalDate.now();
	                	
	                	Beneficiario model = Beneficiario.builder()
	                            .id(UUID.randomUUID())
	                            .name(dtoTemp.getName())
	                            .phoneNumber(dtoTemp.getPhoneNumber())
	                            .birthDate(dtoTemp.getBirthDate())
	                            .insertDate(now)
	                            .updateDate(now)
	                            .documentoList(documentoResourceMapper.toCreationModelList(dtoTemp.getDocumentoList()))
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
	                log.error("Tentativa de mapear DTO {} nulo para modelo {}", BeneficiarioCreateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());
                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                return null;
	            });
	}

	@Override
	public Beneficiario toUpdateModel(BeneficiarioUpdateRequestDto dto) {
	    log.info("Mapeando DTO {} para modelo {}...", BeneficiarioUpdateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());
	    log.debug("dto: {}", dto);

	    return Optional.ofNullable(dto)
	            .map(dtoTemp -> {
	                try {
	                	LocalDate now = LocalDate.now();
	                	
	                	Beneficiario model = Beneficiario.builder()
	                            .id(dtoTemp.getId())
	                            .name(dtoTemp.getName())
	                            .phoneNumber(dtoTemp.getPhoneNumber())
	                            .birthDate(dtoTemp.getBirthDate())
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
	                log.error("Tentativa de mapear DTO {} nulo para modelo {}", BeneficiarioUpdateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());
                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                return null;
	            });
	}

	@Override
	public BeneficiarioResponseDto toResponseDto(Beneficiario model) {
	    log.info("Mapeando modelo {} para DTO {}...", Beneficiario.class.getSimpleName(), BeneficiarioResponseDto.class.getSimpleName());
	    log.info("model: {}", model);
	    return Optional.ofNullable(model)
	            .map(modelTemp -> {
	                try {
	                	BeneficiarioResponseDto dto = BeneficiarioResponseDto.builder()
	                    		.id(modelTemp.getId())
	                    		.name(modelTemp.getName())
	                            .phoneNumber(modelTemp.getPhoneNumber())
	                            .birthDate(modelTemp.getBirthDate())
	                            .insertDate(modelTemp.getInsertDate())
	                            .updateDate(modelTemp.getUpdateDate())
	                            .documentoList(documentoResourceMapper.toResponseDtoList(modelTemp.getDocumentoList()))
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
	                log.error("Tentativa de mapear modelo {} nula para DTO {}", Beneficiario.class.getSimpleName(), BeneficiarioResponseDto.class.getSimpleName());
                    // TODO: podemos retornar null ou lançar uma exceção customizada
	                return null;
	            });
	}

	@Override
	public List<Beneficiario> toCreationModelList(List<BeneficiarioCreateRequestDto> dtos) {
	    log.info("Iniciando mapeamento de lista de DTOs {} para lista de modelos {}", BeneficiarioCreateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());

	    try {
	        List<Beneficiario> models = Optional.ofNullable(dtos)
	            .map(list -> list.stream()
	                             .map(this::toCreationModel)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear lista de {} nula para lista de {}", BeneficiarioCreateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());
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
	public List<Beneficiario> toUpdateModelList(List<BeneficiarioUpdateRequestDto> dtos) {
	    log.info("Iniciando mapeamento de lista de DTOs {} para lista de modelos {}", BeneficiarioUpdateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());

	    try {
	        List<Beneficiario> models = Optional.ofNullable(dtos)
	            .map(list -> list.stream()
	                             .map(this::toUpdateModel)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear lista de DTOs {} nula para lista de modelos {}", BeneficiarioUpdateRequestDto.class.getSimpleName(), Beneficiario.class.getSimpleName());
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
	public List<BeneficiarioResponseDto> toResponseDtoList(List<Beneficiario> models) {
	    log.info("Iniciando mapeamento de lista de modelos {} para lista de DTOs {}", Beneficiario.class.getSimpleName(), BeneficiarioResponseDto.class.getSimpleName());

	    try {
	        List<BeneficiarioResponseDto> dtos = Optional.ofNullable(models)
	            .map(list -> list.stream()
	                             .map(this::toResponseDto)
	                             .collect(Collectors.toList()))
	            .orElseGet(() -> {
	                log.error("Tentativa de mapear lista de modelos {} nula para lista de DTOs {}", Beneficiario.class.getSimpleName(), BeneficiarioResponseDto.class.getSimpleName());
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

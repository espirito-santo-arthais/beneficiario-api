package br.com.ekan.beneficiario.api.resources.mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiaryResponseDto;
import br.com.ekan.beneficiario.api.resources.exceptions.InternalServerErrorResourceException;
import br.com.ekan.beneficiario.api.resources.exceptions.WarningResourceException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeneficiaryResourceMapper 
    implements ResourceMapper<
        BeneficiaryCreateRequestDto, 
        BeneficiaryUpdateRequestDto, 
        BeneficiaryResponseDto, 
        Beneficiary> {

    private final DocumentResourceMapper documentResourceMapper;
    
    public BeneficiaryResourceMapper(@Lazy DocumentResourceMapper documentResourceMapper) {
        this.documentResourceMapper = documentResourceMapper;
    }

    @Override
    public Beneficiary toCreationModel(BeneficiaryCreateRequestDto dto) {
        log.info("Mapeando DTO {} para modelo {}...", BeneficiaryCreateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());
        log.debug("dto: {}", dto);

        return Optional.ofNullable(dto)
                .map(dtoTemp -> {
                    try {
                        LocalDate now = LocalDate.now();
                        
                        UUID id = UUID.randomUUID();
                        
                        Optional.ofNullable(dtoTemp.getDocumentList())
                        		.orElse(Collections.emptyList())
                        		.forEach(document -> document.setBeneficiaryId(id));
                        
                        Beneficiary model = Beneficiary.builder()
                                .id(id)
                                .name(dtoTemp.getName())
                                .phoneNumber(dtoTemp.getPhoneNumber())
                                .birthDate(dtoTemp.getBirthDate())
                                .insertDate(now)
                                .updateDate(now)
                                .documentList(documentResourceMapper.toCreationModelList(dtoTemp.getDocumentList()))
                                .build();
                        log.info("DTO mapeado para modelo com sucesso!");
                        log.debug("model: {}", model);
                        return model;
                    } catch (Exception ex) {
                        String errorMessage = "Erro ao mapear DTO para modelo.";
                        log.error(String.format("%1s. %2s", errorMessage, ex.getMessage()), ex);
                        throw new InternalServerErrorResourceException(errorMessage, ex);
                    }
                })
                .orElseThrow(() -> {
                    String warningMessage = String.format("Tentativa de mapear DTO %1s nulo para modelo %2s.", BeneficiaryCreateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());
                    log.warn(warningMessage);
                    throw new WarningResourceException(warningMessage);
                });
    }

    @Override
    public Beneficiary toUpdateModel(BeneficiaryUpdateRequestDto dto) {
        log.info("Mapeando DTO {} para modelo {}...", BeneficiaryUpdateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());
        log.debug("dto: {}", dto);

        return Optional.ofNullable(dto)
                .map(dtoTemp -> {
                    try {
                        LocalDate now = LocalDate.now();
                        
                        Beneficiary model = Beneficiary.builder()
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
                        String errorMessage = "Erro ao mapear DTO para modelo.";
                        log.error(String.format("%1s. %2s", errorMessage, ex.getMessage()), ex);
                        throw new InternalServerErrorResourceException(errorMessage, ex);
                    }
                })
                .orElseThrow(() -> {
                    String warningMessage = String.format("Tentativa de mapear DTO %1s nulo para modelo %2s.", BeneficiaryUpdateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());
                    log.warn(warningMessage);
                    throw new WarningResourceException(warningMessage);
                });
    }

    @Override
    public BeneficiaryResponseDto toResponseDto(Beneficiary model) {
        log.info("Mapeando modelo {} para DTO {}...", Beneficiary.class.getSimpleName(), BeneficiaryResponseDto.class.getSimpleName());
        log.info("model: {}", model);
        return Optional.ofNullable(model)
                .map(modelTemp -> {
                    try {
                        BeneficiaryResponseDto dto = BeneficiaryResponseDto.builder()
                                .id(modelTemp.getId())
                                .name(modelTemp.getName())
                                .phoneNumber(modelTemp.getPhoneNumber())
                                .birthDate(modelTemp.getBirthDate())
                                .insertDate(modelTemp.getInsertDate())
                                .updateDate(modelTemp.getUpdateDate())
                                .documentList(documentResourceMapper.toResponseDtoList(modelTemp.getDocumentList()))
                                .build();
                        log.info("Modelo mapeado para DTO com sucesso!");
                        log.debug("dto: {}", dto);
                        return dto;
                    } catch (Exception ex) {
                        String errorMessage = "Erro ao mapear modelo para DTO.";
                        log.error(String.format("%1s. %2s", errorMessage, ex.getMessage()), ex);
                        throw new InternalServerErrorResourceException(errorMessage, ex);
                    }
                })
                .orElseThrow(() -> {
                    String warningMessage = String.format("Tentativa de mapear modelo %1s nulo para DTO %2s.", Beneficiary.class.getSimpleName(), BeneficiaryResponseDto.class.getSimpleName());
                    log.warn(warningMessage);
                    throw new WarningResourceException(warningMessage);
                });
    }

    @Override
    public List<Beneficiary> toCreationModelList(List<BeneficiaryCreateRequestDto> dtos) {
        log.info("Iniciando mapeamento de lista de DTOs {} para lista de modelos {}", BeneficiaryCreateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());

        try {
            List<Beneficiary> models = Optional.ofNullable(dtos)
                .map(list -> list.stream()
                                 .map(this::toCreationModel)
                                 .collect(Collectors.toList()))
                .orElseGet(() -> {
                    String warningMessage = String.format("Tentativa de mapear lista de %1s nula para lista de %2s.", BeneficiaryCreateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());
                    log.warn(warningMessage);
                    return Collections.emptyList();
                });

            log.info("Mapeamento de lista de DTOs para lista de modelos concluído com sucesso!");
            return models;
        } catch (Exception ex) {
            String errorMessage = String.format("Erro ao mapear lista de DTOs para lista de modelos: %1s", ex.getMessage());
            log.error(errorMessage, ex);
            throw new InternalServerErrorResourceException(errorMessage, ex);
        }
    }

    @Override
    public List<Beneficiary> toUpdateModelList(List<BeneficiaryUpdateRequestDto> dtos) {
        log.info("Iniciando mapeamento de lista de DTOs {} para lista de modelos {}", BeneficiaryUpdateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());

        try {
            List<Beneficiary> models = Optional.ofNullable(dtos)
                .map(list -> list.stream()
                                 .map(this::toUpdateModel)
                                 .collect(Collectors.toList()))
                .orElseGet(() -> {
                    String warningMessage = String.format("Tentativa de mapear lista de DTOs %1s nula para lista de modelos %2s.", BeneficiaryUpdateRequestDto.class.getSimpleName(), Beneficiary.class.getSimpleName());
                    log.warn(warningMessage);
                    return Collections.emptyList();
                });

            log.info("Mapeamento de lista de DTOs para lista de modelos concluído com sucesso!");
            return models;
        } catch (Exception ex) {
            String errorMessage = String.format("Erro ao mapear lista de DTOs para lista de modelos: %1s", ex.getMessage());
            log.error(errorMessage, ex);
            throw new InternalServerErrorResourceException(errorMessage, ex);
        }
    }

    @Override
    public List<BeneficiaryResponseDto> toResponseDtoList(List<Beneficiary> models) {
        log.info("Iniciando mapeamento de lista de modelos {} para lista de DTOs {}", Beneficiary.class.getSimpleName(), BeneficiaryResponseDto.class.getSimpleName());

        try {
            List<BeneficiaryResponseDto> dtos = Optional.ofNullable(models)
                .map(list -> list.stream()
                                 .map(this::toResponseDto)
                                 .collect(Collectors.toList()))
                .orElseGet(() -> {
                    String warningMessage = String.format("Tentativa de mapear lista de modelos %1s nula para lista de DTOs %2s.", Beneficiary.class.getSimpleName(), BeneficiaryResponseDto.class.getSimpleName());
                    log.warn(warningMessage);
                    return Collections.emptyList();
                });

            log.info("Mapeamento de lista de modelos para lista de DTOs concluído com sucesso!");
            return dtos;
        } catch (Exception ex) {
            String errorMessage = String.format("Erro ao mapear lista de modelos para lista de DTOs: %1s", ex.getMessage());
            log.error(errorMessage, ex);
            throw new InternalServerErrorResourceException(errorMessage, ex);
        }
    }

}

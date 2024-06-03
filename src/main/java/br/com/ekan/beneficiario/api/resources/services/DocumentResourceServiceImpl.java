package br.com.ekan.beneficiario.api.resources.services;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.exceptions.AbstractDomainException;
import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.domain.services.DocumentDomainService;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.AbstractDatabaseException;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.DocumentResourceMapper;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentResourceServiceImpl 
	extends ResourceServiceImpl<
		DocumentCreateRequestDto, 
		DocumentUpdateRequestDto, 
		DocumentResponseDto, 
		DocumentResourceMapper, 
		Document, 
		DocumentDomainService>
	implements DocumentResourceService {

	protected final DocumentResourceMapper mapper;
	protected final DocumentDomainService domainService;

	public DocumentResourceServiceImpl(
			@Lazy DocumentResourceMapper mapper, 
			@Lazy DocumentDomainService domainService) {
		super(mapper, domainService);
        log.info("Continuando a inicialização do serviço...");
		this.mapper = mapper;
		this.domainService = domainService;
	}

	@Override
	public ApiReturn<List<DocumentResponseDto>> getByBeneficiary(UUID beneficiaryId) {
		log.info("Recuperando todos os documentos do beneficiário informado...");
		log.debug("beneficiaryId: {}", beneficiaryId);

		try {
			List<Document> modelList = domainService.getByBeneficiary(beneficiaryId);

			List<DocumentResponseDto> responseDtoList = mapper.toResponseDtoList(modelList);

			String message = String.format("Documentos recuperados com sucesso! Quantidade: %1s", responseDtoList.size());
			ApiReturn<List<DocumentResponseDto>> apiReturn = getApiReturnForData(responseDtoList, HttpStatus.OK, message);

			log.info(message);

			return apiReturn;
		} catch (AbstractDatabaseException | AbstractDomainException ex) {
			ApiReturn<List<DocumentResponseDto>> apiReturn = getApiReturnForException(null, ex);
			return apiReturn;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os documentos.";
			log.error(message, ex);
			ApiReturn<List<DocumentResponseDto>> apiReturn = getApiReturnForException(message, ex);
			return apiReturn;
		}
	}

}

package br.com.ekan.beneficiario.api.resources.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiaryResponseDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentResponseDto;
import br.com.ekan.beneficiario.api.resources.services.BeneficiaryResourceServiceImpl;
import br.com.ekan.beneficiario.api.resources.services.DocumentResourceServiceImpl;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BeneficiarioController implements BeneficiarioApi {
	
	private final String VALIDANTION_AND_MAPPING_MESSAGE = "Validações e mapeamentos da camada HTTP realizados com sucesso!...";
	private final String SERIALIZING_RESULT_MESSAGE = "Serializando o resultado...";
	
	private final BeneficiaryResourceServiceImpl resourceService;
	private final DocumentResourceServiceImpl documentResourceService;
	
	public BeneficiarioController(
			@Lazy BeneficiaryResourceServiceImpl resourceService,
			@Lazy DocumentResourceServiceImpl documentResourceService) {
		log.info("Inicializando o controlador...");
		this.resourceService = resourceService;
		this.documentResourceService = documentResourceService;
	}

	@PostConstruct
	public void postConstruct() {
		log.info("Controlador inicializado com sucesso!...");
	}

	public ResponseEntity<ApiReturn<BeneficiaryResponseDto>> post(BeneficiaryCreateRequestDto createRequestDto) {
		log.info(VALIDANTION_AND_MAPPING_MESSAGE);
		ApiReturn<BeneficiaryResponseDto> apiReturn = resourceService.post(createRequestDto);
		log.info(SERIALIZING_RESULT_MESSAGE);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiReturn);
	}

	public ResponseEntity<ApiReturn<BeneficiaryResponseDto>> patch(UUID id, BeneficiaryUpdateRequestDto updateRequestDto) {
		log.info(VALIDANTION_AND_MAPPING_MESSAGE);
		ApiReturn<BeneficiaryResponseDto> apiReturn = resourceService.patch(id, updateRequestDto);
		log.info(SERIALIZING_RESULT_MESSAGE);
		return ResponseEntity.ok(apiReturn);
	}

	public ResponseEntity<ApiReturn<BeneficiaryResponseDto>> delete(UUID id) {
		log.info(VALIDANTION_AND_MAPPING_MESSAGE);
		ApiReturn<BeneficiaryResponseDto> apiReturn = resourceService.delete(id);
		log.info(SERIALIZING_RESULT_MESSAGE);
		return ResponseEntity.ok(apiReturn);
	}

	public ResponseEntity<ApiReturn<List<BeneficiaryResponseDto>>> getAll() {
		log.info(VALIDANTION_AND_MAPPING_MESSAGE);
		ApiReturn<List<BeneficiaryResponseDto>> apiResponse = resourceService.getAll();
		log.info(SERIALIZING_RESULT_MESSAGE);
		return ResponseEntity.ok(apiResponse);
	}

	public ResponseEntity<ApiReturn<List<DocumentResponseDto>>> getDocumentosByBeneficiarioId(UUID id) {
		log.info(VALIDANTION_AND_MAPPING_MESSAGE);
		ApiReturn<List<DocumentResponseDto>> apiResponse = documentResourceService.getByBeneficiary(id);
		log.info(SERIALIZING_RESULT_MESSAGE);
		return ResponseEntity.ok(apiResponse);
	}

}

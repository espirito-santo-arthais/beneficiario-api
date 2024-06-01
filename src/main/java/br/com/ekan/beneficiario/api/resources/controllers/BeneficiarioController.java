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
import br.com.ekan.beneficiario.api.resources.services.BeneficiarioResourceServiceImpl;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BeneficiarioController implements BeneficiarioApi {
	
	private final BeneficiarioResourceServiceImpl resourceService;
	
	public BeneficiarioController(
			@Lazy BeneficiarioResourceServiceImpl resourceService) {
		this.resourceService = resourceService;
	}
	
	public ResponseEntity<ApiReturn<BeneficiaryResponseDto>> post(BeneficiaryCreateRequestDto createRequestDto) {
		ApiReturn<BeneficiaryResponseDto> apiReturn = resourceService.post(createRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiReturn);
	}

	public ResponseEntity<ApiReturn<BeneficiaryResponseDto>> patch(UUID id, BeneficiaryUpdateRequestDto updateRequestDto) {
		ApiReturn<BeneficiaryResponseDto> apiReturn = resourceService.patch(id, updateRequestDto);
		return ResponseEntity.ok(apiReturn);
	}

	public ResponseEntity<ApiReturn<BeneficiaryResponseDto>> delete(UUID id) {
		ApiReturn<BeneficiaryResponseDto> apiReturn = resourceService.delete(id);
		return ResponseEntity.ok(apiReturn);
	}

	public ResponseEntity<ApiReturn<List<BeneficiaryResponseDto>>> getAll() {
		ApiReturn<List<BeneficiaryResponseDto>> apiResponse = resourceService.getAll();
		return ResponseEntity.ok(apiResponse);
	}

	public ResponseEntity<ApiReturn<List<DocumentResponseDto>>> getDocumentosByBeneficiarioId(UUID id) {
		ApiReturn<List<BeneficiaryResponseDto>> apiResponse = resourceService.getAll();
		return ResponseEntity.ok(apiResponse);
	}

}

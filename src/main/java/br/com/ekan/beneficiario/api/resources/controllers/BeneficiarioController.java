package br.com.ekan.beneficiario.api.resources.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiarioCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiarioUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiarioResponseDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentoResponseDto;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BeneficiarioController implements BeneficiarioApi {
	
	//private final BeneficiarioResourceServiceImpl persisterResourceService;
	
	//public BeneficiarioController(
	//		@Lazy BeneficiarioResourceServiceImpl persisterResourceService) {
	//	this.persisterResourceService = persisterResourceService;
	//}
	
	public ResponseEntity<ApiReturn<BeneficiarioResponseDto>> post(BeneficiarioCreateRequestDto createRequestDto) {
		//ApiReturn<BeneficiarioResponseDto> apiReturn = persisterResourceService.post(createRequestDto);
		//return ResponseEntity.status(HttpStatus.CREATED).body(apiReturn);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	public ResponseEntity<ApiReturn<BeneficiarioResponseDto>> patch(UUID id, BeneficiarioUpdateRequestDto updateRequestDto) {
		//ApiReturn<BeneficiarioResponseDto> apiReturn = persisterResourceService.patch(id, updateRequestDto);
		//return ResponseEntity.ok(apiReturn);
		return ResponseEntity.ok(null);
	}

	public ResponseEntity<ApiReturn<BeneficiarioResponseDto>> delete(UUID id) {
		//ApiReturn<BeneficiarioResponseDto> apiReturn = persisterResourceService.delete(id);
		//return ResponseEntity.ok(apiReturn);
		return ResponseEntity.ok(null);
	}

	public ResponseEntity<ApiReturn<List<BeneficiarioResponseDto>>> getAll() {
		//ApiReturn<List<BeneficiarioResponseDto>> apiResponse = persisterResourceService.getAll();
		//return ResponseEntity.ok(apiResponse);
		return ResponseEntity.ok(null);
	}

	public ResponseEntity<ApiReturn<List<DocumentoResponseDto>>> getDocumentosByBeneficiarioId(UUID id) {
		//ApiReturn<List<BeneficiarioResponseDto>> apiResponse = persisterResourceService.getAll();
		//return ResponseEntity.ok(apiResponse);
		return ResponseEntity.ok(null);
	}

}

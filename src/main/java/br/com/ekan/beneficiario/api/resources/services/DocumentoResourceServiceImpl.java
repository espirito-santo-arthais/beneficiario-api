package br.com.ekan.beneficiario.api.resources.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Documento;
import br.com.ekan.beneficiario.api.domain.services.DomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentoCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentoUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentoResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.ResourceMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentoResourceServiceImpl 
	extends ResourceServiceImpl<
		DocumentoCreateRequestDto, 
		DocumentoUpdateRequestDto, 
		DocumentoResponseDto, 
		ResourceMapper<
			DocumentoCreateRequestDto, 
			DocumentoUpdateRequestDto, 
			DocumentoResponseDto, 
			Documento>, 
		Documento, 
		BeneficiarioDomainService>
	implements DocumentoResourceService<
		DocumentoCreateRequestDto, 
		DocumentoUpdateRequestDto, 
		DocumentoResponseDto, 
		ResourceMapper<
			DocumentoCreateRequestDto, 
			DocumentoUpdateRequestDto, 
			DocumentoResponseDto, 
			Documento>, 
		Documento, 
		BeneficiarioDomainService> {

	protected final ResourceMapper<DocumentoCreateRequestDto, DocumentoUpdateRequestDto, DocumentoResponseDto, Documento> mapper;
	protected final DomainService<Documento> domainService;

	public DocumentoResourceServiceImpl(
			@Lazy ResourceMapper<DocumentoCreateRequestDto, DocumentoUpdateRequestDto, DocumentoResponseDto,	Documento> mapper, 
			@Lazy DomainService<Documento> domainService) {
		super(mapper, domainService);
		this.mapper = mapper;
		this.domainService = domainService;
	}

}

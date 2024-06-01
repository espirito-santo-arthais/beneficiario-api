package br.com.ekan.beneficiario.api.resources.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Document;
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
			Document>, 
		Document, 
		BeneficiarioDomainService>
	implements DocumentoResourceService<
		DocumentoCreateRequestDto, 
		DocumentoUpdateRequestDto, 
		DocumentoResponseDto, 
		ResourceMapper<
			DocumentoCreateRequestDto, 
			DocumentoUpdateRequestDto, 
			DocumentoResponseDto, 
			Document>, 
		Document, 
		BeneficiarioDomainService> {

	protected final ResourceMapper<DocumentoCreateRequestDto, DocumentoUpdateRequestDto, DocumentoResponseDto, Document> mapper;
	protected final DomainService<Document> domainService;

	public DocumentoResourceServiceImpl(
			@Lazy ResourceMapper<DocumentoCreateRequestDto, DocumentoUpdateRequestDto, DocumentoResponseDto,	Document> mapper, 
			@Lazy DomainService<Document> domainService) {
		super(mapper, domainService);
		this.mapper = mapper;
		this.domainService = domainService;
	}

}

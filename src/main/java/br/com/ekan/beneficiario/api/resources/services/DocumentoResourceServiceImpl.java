package br.com.ekan.beneficiario.api.resources.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.domain.services.DocumentDomainService;
import br.com.ekan.beneficiario.api.domain.services.DomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.ResourceMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentoResourceServiceImpl 
	extends ResourceServiceImpl<
		DocumentCreateRequestDto, 
		DocumentUpdateRequestDto, 
		DocumentResponseDto, 
		ResourceMapper<
			DocumentCreateRequestDto, 
			DocumentUpdateRequestDto, 
			DocumentResponseDto, 
			Document>, 
		Document, 
		DocumentDomainService>
	implements DocumentoResourceService<
		DocumentCreateRequestDto, 
		DocumentUpdateRequestDto, 
		DocumentResponseDto, 
		ResourceMapper<
			DocumentCreateRequestDto, 
			DocumentUpdateRequestDto, 
			DocumentResponseDto, 
			Document>, 
		Document, 
		DocumentDomainService> {

	protected final ResourceMapper<DocumentCreateRequestDto, DocumentUpdateRequestDto, DocumentResponseDto, Document> mapper;
	protected final DomainService<Document> domainService;

	public DocumentoResourceServiceImpl(
			@Lazy ResourceMapper<DocumentCreateRequestDto, DocumentUpdateRequestDto, DocumentResponseDto,	Document> mapper, 
			@Lazy DomainService<Document> domainService) {
		super(mapper, domainService);
		this.mapper = mapper;
		this.domainService = domainService;
	}

}

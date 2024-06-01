package br.com.ekan.beneficiario.api.infrastructure.database.services;

import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.DocumentEntity;
import br.com.ekan.beneficiario.api.infrastructure.database.mappers.DocumentDatabaseMapper;
import br.com.ekan.beneficiario.api.infrastructure.database.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentDatabaseServiceImpl 
		extends DatabaseServiceImpl<Document, DocumentEntity>
		implements DocumentDatabaseService {

	private final DocumentRepository repository;
	private final DocumentDatabaseMapper mapper;

	public DocumentDatabaseServiceImpl(
			DocumentRepository repository, 
			DocumentDatabaseMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

}

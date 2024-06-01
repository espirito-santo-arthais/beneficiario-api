package br.com.ekan.beneficiario.api.infrastructure.database.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.DocumentEntity;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.InternalServerErrorDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.NotFoundDatabaseException;
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

	@Override
	public List<Document> getByBeneficiary(UUID beneficiaryId) {
		log.info("Recuperando os documentos do beneficiario informado...");

		try {
			Beneficiary beneficiary = Beneficiary.builder().id(beneficiaryId).build();
			
			List<DocumentEntity> entityList = repository.findByBeneficiary(beneficiary);
			if (entityList.isEmpty()) {
				String message = "Registros não encontrados.";
				log.warn(message);
				throw new NotFoundDatabaseException(message);
			}

			List<Document> modelList = mapper.toModelList(entityList);

			log.info("Documentos recuperados com sucesso! Quantidade: {}", modelList.size());

			return modelList;
		} catch (NotFoundDatabaseException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os documentos.";
			log.error(message, ex);
			throw new InternalServerErrorDatabaseException(message, ex);
		}
	}

}

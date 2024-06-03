package br.com.ekan.beneficiario.api.infrastructure.database.services;

import java.util.List;
import java.util.UUID;

import br.com.ekan.beneficiario.api.domain.enums.DocumentTypeEnum;
import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.DocumentEntity;

public interface DocumentDatabaseService extends DatabaseService<Document, DocumentEntity>  {

	boolean existsByBeneficiaryAndDocumentType(UUID beneficiaryId, DocumentTypeEnum documentTypeEnum);

	List<Document> getByBeneficiary(UUID beneficiaryId);

}

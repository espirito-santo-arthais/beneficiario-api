package br.com.ekan.beneficiario.api.infrastructure.database.services;

import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.DocumentEntity;

public interface DocumentDatabaseService extends DatabaseService<Document, DocumentEntity>  {

}

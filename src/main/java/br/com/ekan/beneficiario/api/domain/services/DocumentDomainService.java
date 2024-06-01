package br.com.ekan.beneficiario.api.domain.services;

import java.util.List;
import java.util.UUID;

import br.com.ekan.beneficiario.api.domain.models.Document;

public interface DocumentDomainService extends DomainService<Document> {

	List<Document> getByBeneficiary(UUID beneficiaryId);

}

package br.com.ekan.beneficiario.api.infrastructure.database.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.DocumentEntity;

@Repository
public interface DocumentRepository extends CrudRepository<DocumentEntity, String> {

	List<Beneficiary> findByBeneficiary(Beneficiary beneficiary);

}

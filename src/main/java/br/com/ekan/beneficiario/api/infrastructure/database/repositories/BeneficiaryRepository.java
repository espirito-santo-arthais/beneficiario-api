package br.com.ekan.beneficiario.api.infrastructure.database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ekan.beneficiario.api.infrastructure.database.entities.BeneficiaryEntity;


@Repository
public interface BeneficiaryRepository extends CrudRepository<BeneficiaryEntity, String> {

}

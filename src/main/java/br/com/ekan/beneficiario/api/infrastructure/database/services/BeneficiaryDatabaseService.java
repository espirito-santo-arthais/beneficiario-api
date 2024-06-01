package br.com.ekan.beneficiario.api.infrastructure.database.services;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.BeneficiaryEntity;

public interface BeneficiaryDatabaseService extends DatabaseService<Beneficiary, BeneficiaryEntity>    {

}

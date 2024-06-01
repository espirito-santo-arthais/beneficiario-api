package br.com.ekan.beneficiario.api.infrastructure.database.services;

import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.BeneficiaryEntity;
import br.com.ekan.beneficiario.api.infrastructure.database.mappers.BeneficiaryDatabaseMapper;
import br.com.ekan.beneficiario.api.infrastructure.database.repositories.BeneficiaryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiaryDatabaseServiceImpl
		extends DatabaseServiceImpl<Beneficiary, BeneficiaryEntity> 
		implements BeneficiaryDatabaseService {

	private final BeneficiaryRepository repository;
	private final BeneficiaryDatabaseMapper mapper;

	public BeneficiaryDatabaseServiceImpl(
			BeneficiaryRepository repository, 
			BeneficiaryDatabaseMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

}

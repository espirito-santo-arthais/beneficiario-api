package br.com.ekan.beneficiario.api.resources.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.domain.services.BeneficiaryDomainService;
import br.com.ekan.beneficiario.api.domain.services.DomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiaryResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.ResourceMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiarioResourceServiceImpl
		extends ResourceServiceImpl<
			BeneficiaryCreateRequestDto, 
			BeneficiaryUpdateRequestDto, 
			BeneficiaryResponseDto, 
			ResourceMapper<
				BeneficiaryCreateRequestDto, 
				BeneficiaryUpdateRequestDto, 
				BeneficiaryResponseDto, 
				Beneficiary>, 
			Beneficiary, 
			BeneficiaryDomainService>
		implements BeneficiarioResourceService<
			BeneficiaryCreateRequestDto, 
			BeneficiaryUpdateRequestDto, 
			BeneficiaryResponseDto, 
			ResourceMapper<
				BeneficiaryCreateRequestDto, 
				BeneficiaryUpdateRequestDto, 
				BeneficiaryResponseDto, 
				Beneficiary>, 
			Beneficiary, 
			BeneficiaryDomainService> {

	protected final ResourceMapper<BeneficiaryCreateRequestDto, BeneficiaryUpdateRequestDto, BeneficiaryResponseDto, Beneficiary> mapper;
	protected final DomainService<Beneficiary> domainService;

	public BeneficiarioResourceServiceImpl(
			@Lazy ResourceMapper<BeneficiaryCreateRequestDto, BeneficiaryUpdateRequestDto, BeneficiaryResponseDto, Beneficiary> mapper, 
			@Lazy DomainService<Beneficiary> domainService) {
		super(mapper, domainService);
		this.mapper = mapper;
		this.domainService = domainService;
	}

}

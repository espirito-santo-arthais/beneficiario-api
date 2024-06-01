package br.com.ekan.beneficiario.api.resources.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Beneficiario;
import br.com.ekan.beneficiario.api.domain.services.DomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiarioCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiarioUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiarioResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.ResourceMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiarioResourceServiceImpl
		extends ResourceServiceImpl<
			BeneficiarioCreateRequestDto, 
			BeneficiarioUpdateRequestDto, 
			BeneficiarioResponseDto, 
			ResourceMapper<
				BeneficiarioCreateRequestDto, 
				BeneficiarioUpdateRequestDto, 
				BeneficiarioResponseDto, 
				Beneficiario>, 
			Beneficiario, 
			BeneficiarioDomainService>
		implements BeneficiarioResourceService<
			BeneficiarioCreateRequestDto, 
			BeneficiarioUpdateRequestDto, 
			BeneficiarioResponseDto, 
			ResourceMapper<
				BeneficiarioCreateRequestDto, 
				BeneficiarioUpdateRequestDto, 
				BeneficiarioResponseDto, 
				Beneficiario>, 
			Beneficiario, 
			BeneficiarioDomainService> {

	protected final ResourceMapper<BeneficiarioCreateRequestDto, BeneficiarioUpdateRequestDto, BeneficiarioResponseDto, Beneficiario> mapper;
	protected final DomainService<Beneficiario> domainService;

	public BeneficiarioResourceServiceImpl(
			@Lazy ResourceMapper<BeneficiarioCreateRequestDto, BeneficiarioUpdateRequestDto, BeneficiarioResponseDto, Beneficiario> mapper, 
			@Lazy DomainService<Beneficiario> domainService) {
		super(mapper, domainService);
		this.mapper = mapper;
		this.domainService = domainService;
	}

}

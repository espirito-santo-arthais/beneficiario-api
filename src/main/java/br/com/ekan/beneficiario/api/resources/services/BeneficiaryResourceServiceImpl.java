package br.com.ekan.beneficiario.api.resources.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.domain.services.BeneficiaryDomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiaryResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.BeneficiaryResourceMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiaryResourceServiceImpl
        extends ResourceServiceImpl<
                BeneficiaryCreateRequestDto,
                BeneficiaryUpdateRequestDto,
                BeneficiaryResponseDto,
                BeneficiaryResourceMapper,
                Beneficiary,
                BeneficiaryDomainService>
        implements BeneficiaryResourceService {

    protected final BeneficiaryResourceMapper mapper;
    protected final BeneficiaryDomainService domainService;

    public BeneficiaryResourceServiceImpl(
            @Lazy BeneficiaryResourceMapper mapper,
            @Lazy BeneficiaryDomainService domainService) {
        super(mapper, domainService);
        log.info("Continuando a inicialização do serviço...");
        this.mapper = mapper;
        this.domainService = domainService;
    }
    
}

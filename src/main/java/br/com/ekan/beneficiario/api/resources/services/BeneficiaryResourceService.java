package br.com.ekan.beneficiario.api.resources.services;

import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.domain.services.BeneficiaryDomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiaryResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.BeneficiaryResourceMapper;

/**
 * 
 * @param <CQ> Extends AbstractCreateRequestDto
 * @param <UQ> Extends AbstractUpdateRequestDto
 * @param <P>  Extends AbstractResponseDTO
 * @param <PP> Extends ResourceMapper
 * @param <M>  Extends AbstractModel
 * @param <D>  Extends DomainService
 */
public interface BeneficiaryResourceService
		extends ResourceService<
		BeneficiaryCreateRequestDto, 
		BeneficiaryUpdateRequestDto, 
		BeneficiaryResponseDto, 
		BeneficiaryResourceMapper,
		Beneficiary, 
		BeneficiaryDomainService> {

}
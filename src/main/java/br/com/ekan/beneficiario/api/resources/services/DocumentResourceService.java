package br.com.ekan.beneficiario.api.resources.services;

import java.util.List;
import java.util.UUID;

import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.domain.services.DocumentDomainService;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.DocumentUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentResponseDto;
import br.com.ekan.beneficiario.api.resources.mappers.DocumentResourceMapper;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;

/**
 * 
 * @param <CQ> Extends AbstractCreateRequestDto
 * @param <UQ> Extends AbstractUpdateRequestDto
 * @param <P> Extends AbstractResponseDTO
 * @param <PP> Extends ResourceMapper
 * @param <M> Extends AbstractModel
 * @param <D> Extends DomainService
 */
public interface DocumentResourceService 
	extends ResourceService<
		DocumentCreateRequestDto, 
		DocumentUpdateRequestDto, 
		DocumentResponseDto, 
		DocumentResourceMapper, 
		Document, 
		DocumentDomainService> {

	ApiReturn<List<DocumentResponseDto>> getByBeneficiary(UUID beneficiaryId);

}
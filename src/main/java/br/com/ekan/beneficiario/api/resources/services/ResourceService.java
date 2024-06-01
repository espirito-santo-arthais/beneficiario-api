package br.com.ekan.beneficiario.api.resources.services;

import java.util.List;
import java.util.UUID;

import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;

/**
 * Interface para os serviços da camada Resources.
 * 
 * @param <CQ> Extends AbstractCreateRequestDto
 * @param <UQ> Extends AbstractUpdateRequestDto
 * @param <P> Extends AbstractResponseDTO
 * @param <PP> Extends ResourceMapper
 * @param <M> Extends AbstractModel
 * @param <D> Extends DomainService
 */
public interface ResourceService<CQ, UQ, P, PP, M, D> {

	ApiReturn<P> post(CQ request);

	ApiReturn<P> patch(UUID id, UQ request);

	ApiReturn<P> delete(UUID id);

	ApiReturn<P> getById(UUID id);

	ApiReturn<List<P>> getAll();

}
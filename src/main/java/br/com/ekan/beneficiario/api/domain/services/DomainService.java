package br.com.ekan.beneficiario.api.domain.services;

import java.util.List;
import java.util.UUID;

public interface DomainService<M> {

	M post(final M model);

	M patch(final UUID id, final M model);

	M delete(final UUID id);

	M getById(final UUID id);

	List<M> getAll();

}

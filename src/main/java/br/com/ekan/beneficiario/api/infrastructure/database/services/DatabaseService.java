package br.com.ekan.beneficiario.api.infrastructure.database.services;

import java.util.List;
import java.util.UUID;

public interface DatabaseService<M, E> {

	M post(M modelo);

	M patch(UUID id, M modelo);

	M delete(UUID id);

	M getById(UUID id);

	List<M> getAll();

	// Contagens

	long count();
}
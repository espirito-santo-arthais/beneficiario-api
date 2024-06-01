package br.com.ekan.beneficiario.api.infrastructure.database.mappers;

import java.util.List;

import br.com.ekan.beneficiario.api.domain.models.AbstractModel;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.AbstractEntity;


public interface DatabaseMapper<M extends AbstractModel, E extends AbstractEntity> {

	E toEntity(M model);

	M toModel(E entity);

	List<E> toEntityList(List<M> models);

	public List<M> toModelList(List<E> entities);

}
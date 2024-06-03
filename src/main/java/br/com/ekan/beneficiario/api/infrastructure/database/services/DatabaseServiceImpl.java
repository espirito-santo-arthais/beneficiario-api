package br.com.ekan.beneficiario.api.infrastructure.database.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;

import br.com.ekan.beneficiario.api.domain.models.AbstractModel;
import br.com.ekan.beneficiario.api.infrastructure.database.entities.AbstractEntity;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.InternalServerErrorDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.NotFoundDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.WarningDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.mappers.DatabaseMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DatabaseServiceImpl<M extends AbstractModel, E extends AbstractEntity> implements DatabaseService<M, E> {

	protected final CrudRepository<E, String> repository;
	protected final DatabaseMapper<M, E> mapper;

	public DatabaseServiceImpl(CrudRepository<E, String> repository, DatabaseMapper<M, E> mapper) {
		log.info("Inicializando o serviço...");
		this.repository = repository;
		this.mapper = mapper;
	}

	@PostConstruct
	public void postConstruct() {
		log.info("Serviço inicializado com sucesso!...");
	}

	@Override
	public M post(M model) {
		log.info("Salvando o modelo...");
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.warn(message);
			throw new WarningDatabaseException(message);
		}

		try {
			E entity = mapper.toEntity(model);

			entity = repository.save(entity);

			model = mapper.toModel(entity);

			log.info("Modelo salvo com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (Exception ex) {
			String messge = "Não foi possível salvar o modelo.";
			log.error(messge, ex);
			throw new InternalServerErrorDatabaseException(messge, ex);
		}
	}

	@Override
	public M patch(UUID id, M model) {
		log.info("Atualizando o modelo...");
		log.debug("id: {}", id);
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.warn(message);
			throw new WarningDatabaseException(message);
		}
		if (model.getId().compareTo(id) != 0) {
			Object[] args = { "model.id", "id", model.getId(), id };
			String message = String.format("O atributo %1$s não pode ser diferente do parâmetro %2$s. %1$s = %3$s, %2$s = %4$s", args);
			log.warn(message);
			throw new WarningDatabaseException(message);
		}

		try {
			Optional<E> optionalEntity = repository.findById(model.getId().toString());
			if (!optionalEntity.isPresent()) {
				String message = "Registro não encontrado.";
				log.warn(message);
				throw new NotFoundDatabaseException(message);
			}

			E entity = mapper.toEntity(model);

			entity = repository.save(entity);

			model = mapper.toModel(entity);

			log.info("Modelo atualizado com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (NotFoundDatabaseException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível atualizar o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDatabaseException(message, ex);
		}
	}

	@Override
	public M delete(UUID id) {
		log.info("Excluindo o modelo... ID = {}", id);

		try {
			Optional<E> optionalEntity = repository.findById(id.toString());
			if (!optionalEntity.isPresent()) {
				String message = "Registro não encontrado.";
				log.warn(message);
				throw new NotFoundDatabaseException(message);
			}
			E entity = optionalEntity.get();
			
			repository.deleteById(entity.getId().toString());

			M model = mapper.toModel(entity);

			log.info("Modelo excluído com sucesso!");
			log.debug("model: {}", model);
			
			return model;
		} catch (NotFoundDatabaseException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível excluir o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDatabaseException(message, ex);
		}
	}

	@Override
	public M getById(UUID id) {
		log.info("Recuperando o modelo... ID = {}", id);

		try {
			Optional<E> optionalEntity = repository.findById(id.toString());
			if (!optionalEntity.isPresent()) {
				String message = "Registro não encontrado.";
				log.warn(message);
				throw new NotFoundDatabaseException(message);
			}
			E entity = optionalEntity.get();

			M model = mapper.toModel(entity);

			log.info("Modelo recuperado com sucesso!");
			log.debug("modelo: {}", model);

			return model;
		} catch (NotFoundDatabaseException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDatabaseException(message, ex);
		}
	}

	@Override
	public List<M> getAll() {
		log.info("Recuperando todos os modelos...");

		try {
			Iterable<E> entityIterable = repository.findAll();
			List<E> entityList = StreamSupport.stream(entityIterable.spliterator(), false).collect(Collectors.toList());
			if (entityList.isEmpty()) {
				String message = "Registros não encontrados.";
				log.warn(message);
				throw new NotFoundDatabaseException(message);
			}

			List<M> modelList = mapper.toModelList(entityList);

			log.info("Modelos recuperados com sucesso! Quantidade: {}", modelList.size());

			return modelList;
		} catch (NotFoundDatabaseException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os modelos.";
			log.error(message, ex);
			throw new InternalServerErrorDatabaseException(message, ex);
		}
	}

	// Contagens

	@Override
	public long count() {
		log.info("Contanto os modelos...");

		try {
			long count = repository.count();

			log.info("Modelos contados com secusso! Quantidade: {}", count);

			return count;
		} catch (Exception ex) {
			String message = "Não foi possível contar os modelos.";
			log.error(message, ex);
			throw new InternalServerErrorDatabaseException(message, ex);
		}
	}

}
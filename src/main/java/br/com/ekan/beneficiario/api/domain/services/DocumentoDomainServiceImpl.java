package br.com.ekan.beneficiario.api.domain.services;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentoDomainServiceImpl implements DocumentoDomainService {

	@Lazy @Autowired
	private CalendarDomainServiceImpl calendarDomainService;

	protected final ProgramUserIntegrationResourceService<
		ProgramUserIntegrationCreateRequestDto, 
		ProgramUserIntegrationUpdateRequestDto,
		ProgramUserIntegrationResponseDto,
		ProgramUserIntegrationResourceMapper,
		ProgramUserIntegration, 
		DocumentoDomainService> resourceService;
	protected final DocumentDatabaseService databaseService;

	public DocumentoDomainServiceImpl(
			@Lazy ProgramUserIntegrationResourceService<
				ProgramUserIntegrationCreateRequestDto,
				ProgramUserIntegrationUpdateRequestDto,
				ProgramUserIntegrationResponseDto, 
				ProgramUserIntegrationResourceMapper,
				ProgramUserIntegration,
				DocumentoDomainService> resourceService,
			@Lazy DocumentDatabaseService databaseService) {
		this.resourceService = resourceService;
		this.databaseService = databaseService;
	}

	@Override
	public ProgramUserIntegration post(final ProgramUserIntegration model) {
		log.info("Salvando o modelo...");
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.error(message);
			throw new InternalServerWarningDomainException(message);
		}

		try {
			Calendar calendar = calendarDomainService.getById(model.getCalendar().getId());
			model.setCalendar(calendar);
			
			ProgramUserIntegration createdModel = databaseService.post(model);

			final ProgramUserIntegration finalCreatedModelForFeign = createdModel; // Cria uma cópia final do model

			// TODO: implementar a chamada ao Client Feign, se cabível

			createdModel = databaseService.getById(createdModel.getId());

			log.info("Modelo salvo com sucesso!");
			log.debug("createdModel: {}", createdModel);

			return createdModel;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractClientException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível salvar o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public ProgramUserIntegration patch(final UUID id, final ProgramUserIntegration model) {
		log.info("Atualizando o modelo...");
		log.debug("id: {}", id);
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.error(message);
			throw new InternalServerWarningDomainException(message);
		}
		if (model.getId().compareTo(id) != 0) {
			Object[] args = { "model.id", "id", model.getId(), id };
			String message = String.format("O atributo %1s não pode ser diferente do parâmetro %2s. %1s = %3s, %2s = %4s", args);
			log.error(message);
			throw new InternalServerWarningDomainException(message);
		}

		try {
			ProgramUserIntegration storedModel = databaseService.getById(model.getId());
			if (model.hasUpdate()) {
	            if (model.getInternalPlatformEnum() != null && !model.getInternalPlatformEnum().equals(storedModel.getInternalPlatformEnum())) {
	                storedModel.setInternalPlatformEnum(model.getInternalPlatformEnum());
	            }
	            
	            // Verifica se o mapa não é null e depois se é diferente do mapa armazenado
	            if (model.getProgramUserIntegrationPropertyMap() != null && !model.getProgramUserIntegrationPropertyMap().equals(storedModel.getProgramUserIntegrationPropertyMap())) {
	                storedModel.setProgramUserIntegrationPropertyMap(new HashMap<>(model.getProgramUserIntegrationPropertyMap()));
	            } else if (model.getProgramUserIntegrationPropertyMap() == null && storedModel.getProgramUserIntegrationPropertyMap() != null) {
	                // Caso o mapa em model seja null e o armazenado não, atualiza para null
	                storedModel.setProgramUserIntegrationPropertyMap(null);
	            }

				storedModel = databaseService.post(storedModel);
			}

			final ProgramUserIntegration finalCreatedModelForFeign = storedModel; // Cria uma cópia final do model

			// TODO: implementar a chamada ao Client Feign, se cabível

			storedModel = databaseService.getById(model.getId());

			log.info("Modelo atualizado com sucesso!");
			log.debug("storedModel: {}", storedModel);

			return storedModel;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractClientException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível atualizar o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public ProgramUserIntegration delete(final UUID id) {
		log.info("Excluindo o modelo... ID = {}", id);

		try {
			ProgramUserIntegration model = databaseService.delete(id);

			// TODO: implementar a chamada ao Client Feign, se cabível

			log.info("Modelo excluído com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractClientException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível excluir o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public ProgramUserIntegration getById(final UUID id) {
		log.info("Recuperando o modelo... ID = {}", id);

		try {
			ProgramUserIntegration model = databaseService.getById(id);

			// TODO: implementar a chamada ao Client Feign, se cabível

			log.info("Modelo recuperado com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractClientException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar o modelo.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public List<ProgramUserIntegration> getAll() {
		log.info("Recuperando todos os modelos...");

		try {
			List<ProgramUserIntegration> modelList = databaseService.getAll();

			// TODO: implementar a chamada ao Client Feign, se cabível

			log.info("Modelos recuperados com sucesso! Quantidade: {}", modelList.size());

			return modelList;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractClientException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os modelos.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	// Contagens

	@Override
	public long count() {
		log.info("Contanto os modelos...");

		try {
			long count = databaseService.count();

			// TODO: implementar a chamada ao Client Feign, se cabível

			log.info("Modelos contados com secusso! Quantidade: {}", count);

			return count;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractClientException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível contar os modelos.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

}
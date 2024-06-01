package br.com.ekan.beneficiario.api.domain.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.exceptions.AbstractDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.InternalServerErrorDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.WarningDomainException;
import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.AbstractDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiarioDomainServiceImpl implements BeneficiarioDomainService {

	//protected final BeneficiarioDatabaseService databaseService;

	//public BeneficiarioDomainServiceImpl(
	//		@Lazy BeneficiarioDatabaseService databaseService) {
	//	this.databaseService = databaseService;
	//}

	@Override
	public Beneficiary post(final Beneficiary model) {
		log.info("Salvando o modelo...");
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.error(message);
			throw new WarningDomainException(message);
		}

		try {
			Beneficiary createdModel = databaseService.post(model);

			final Beneficiary finalCreatedModelForFeign = createdModel; // Cria uma cópia final do model

			// TODO: implementar a chamada ao Client Feign, se cabível

			createdModel = databaseService.getById(createdModel.getId());

			log.info("Modelo salvo com sucesso!");
			log.debug("storedModel: {}", createdModel);

			return createdModel;
		} catch (AbstractDatabaseException ex) {
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
	public Beneficiary patch(final UUID id, final Beneficiary model) {
		log.info("Atualizando o modelo...");
		log.debug("id: {}", id);
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.error(message);
			throw new WarningDomainException(message);
		}
		if (model.getId().compareTo(id) != 0) {
			Object[] args = { "model.id", "id", model.getId(), id };
			String message = String
					.format("O atributo %1s não pode ser diferente do parâmetro %2s. %1s = %3s, %2s = %4s", args);
			log.error(message);
			throw new WarningDomainException(message);
		}

		try {
			Beneficiary storedModel = databaseService.getById(model.getId());
			if (model.hasUpdate()) {
	            if (!Objects.equals(model.getName(), storedModel.getName())) {
	                storedModel.setName(model.getName());
	            }
	            if (!Objects.equals(model.getPhoneNumber(), storedModel.getPhoneNumber())) {
	                storedModel.setPhoneNumber(model.getPhoneNumber());
	            }
	            if (!Objects.equals(model.getBirthDate(), storedModel.getBirthDate())) {
	                storedModel.setBirthDate(model.getBirthDate());
	            }
	            if (!Objects.equals(model.getActive(), storedModel.getActive())) {
	                storedModel.setActive(model.getActive());
	            }

				storedModel = databaseService.patch(id, storedModel);
			}

			final Beneficiary finalCreatedModelForFeign = storedModel; // Cria uma cópia final do model
			
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
	public Beneficiary delete(final UUID id) {
		log.info("Excluindo o modelo... ID = {}", id);

		try {
			Beneficiary model = databaseService.delete(id);

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
	public Beneficiary getById(final UUID id) {
		log.info("Recuperando o modelo... ID = {}", id);

		try {
			Beneficiary model = databaseService.getById(id);

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
	public List<Beneficiary> getAll() {
		log.info("Recuperando todos os modelos...");

		try {
			List<Beneficiary> modelList = databaseService.getAll();

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

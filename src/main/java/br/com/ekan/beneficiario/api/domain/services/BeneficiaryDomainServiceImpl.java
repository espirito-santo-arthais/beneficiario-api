package br.com.ekan.beneficiario.api.domain.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.exceptions.AbstractDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.InternalServerErrorDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.WarningDomainException;
import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.AbstractDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.services.BeneficiaryDatabaseService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiaryDomainServiceImpl implements BeneficiaryDomainService {

	private final BeneficiaryDatabaseService databaseService;

	public BeneficiaryDomainServiceImpl(@Lazy BeneficiaryDatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	@Override
	public Beneficiary post(final Beneficiary model) {
		log.info("Salvando o beneficiário...");
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.warn(message);
			throw new WarningDomainException(message);
		}

		try {
			Beneficiary createdModel = databaseService.post(model);

			// Garante que qualquer atualização feita na camada de persistência será
			// recuperada.
			createdModel = databaseService.getById(createdModel.getId());

			log.info("Beneficiário salvo com sucesso!");
			log.debug("storedModel: {}", createdModel);

			return createdModel;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível salvar o beneficiário.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public Beneficiary patch(final UUID id, final Beneficiary model) {
		log.info("Atualizando o beneficiário...");
		log.debug("id: {}", id);
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.warn(message);
			throw new WarningDomainException(message);
		}
		if (model.getId().compareTo(id) != 0) {
			Object[] args = { "model.id", "id", model.getId(), id };
			String message = String.format("O atributo %1s não pode ser diferente do parâmetro %2s. %1s = %3s, %2s = %4s", args);
			log.warn(message);
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
	            if (!Objects.equals(model.getInsertDate(), storedModel.getInsertDate())) {
	                storedModel.setInsertDate(model.getInsertDate());
	            }
	            if (!Objects.equals(model.getUpdateDate(), storedModel.getUpdateDate())) {
	                storedModel.setUpdateDate(model.getUpdateDate());
	            }

				storedModel = databaseService.patch(id, storedModel);
			}

			// Garante que qualquer atualização feita na camada de persistência será
			// recuperada.
			storedModel = databaseService.getById(model.getId());

			log.info("Beneficiário atualizado com sucesso!");
			log.debug("storedModel: {}", storedModel);

			return storedModel;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível atualizar o beneficiário.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public Beneficiary delete(final UUID id) {
		log.info("Excluindo o beneficiário... ID = {}", id);

		try {
			Beneficiary model = databaseService.delete(id);

			log.info("Beneficiário excluído com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível excluir o beneficiário.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public Beneficiary getById(final UUID id) {
		log.info("Recuperando o beneficiário... ID = {}", id);

		try {
			Beneficiary model = databaseService.getById(id);

			log.info("Beneficiário recuperado com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar o beneficiário.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public List<Beneficiary> getAll() {
		log.info("Recuperando todos os beneficiários...");

		try {
			List<Beneficiary> modelList = databaseService.getAll();

			log.info("Beneficiário recuperados com sucesso! Quantidade: {}", modelList.size());

			return modelList;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os beneficiários.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	// Contagens

	@Override
	public long count() {
		log.info("Contanto os beneficiários...");

		try {
			long count = databaseService.count();

			log.info("Beneficiários contados com secusso! Quantidade: {}", count);

			return count;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível contar os beneficiários.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

}

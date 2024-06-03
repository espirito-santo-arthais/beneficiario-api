package br.com.ekan.beneficiario.api.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.exceptions.AbstractDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.InternalServerErrorDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.WarningDomainException;
import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.AbstractDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.services.DocumentDatabaseService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentDomainServiceImpl implements DocumentDomainService {

	private final DocumentDatabaseService databaseService;
	private BeneficiaryDomainServiceImpl beneficiaryDomainService;

	public DocumentDomainServiceImpl(
			@Lazy DocumentDatabaseService databaseService,
			@Lazy BeneficiaryDomainServiceImpl beneficiaryDomainService) {
		log.info("Inicializando o serviço...");
		this.databaseService = databaseService;
		this.beneficiaryDomainService = beneficiaryDomainService;
	}

	@PostConstruct
	public void postConstruct() {
		log.info("Serviço inicializado com sucesso!...");
	}

	@Override
	public Document post(final Document model) {
		log.info("Salvando o documento...");
		log.debug("model: {}", model);

		if (model.getId() == null) {
			Object[] args = { "model.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.error(message);
			throw new WarningDomainException(message);
		}

		try {
			Beneficiary beneficiary = beneficiaryDomainService.getById(model.getBeneficiary().getId());
			model.setBeneficiary(beneficiary);
			
			Document createdModel = databaseService.post(model);

			// Garante que qualquer atualização feita na camada de persistência será
			// recuperada.
			createdModel = databaseService.getById(createdModel.getId());

			log.info("Documento salvo com sucesso!");
			log.debug("createdModel: {}", createdModel);

			return createdModel;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível salvar o documento.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public Document patch(final UUID id, final Document model) {
		log.info("Atualizando o documento...");
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
			String message = String.format("O atributo %1$s não pode ser diferente do parâmetro %2$s. %1$s = %3$s, %2$s = %4$s", args);
			log.warn(message);
			throw new WarningDomainException(message);
		}

		try {
			Document storedModel = databaseService.getById(model.getId());
			if (model.hasUpdate()) {
	            if (model.getDocumentType() != null && !model.getDocumentType().equals(storedModel.getDocumentType())) {
	                storedModel.setDocumentType(model.getDocumentType());
	            }
	            if (model.getDescription() != null && !model.getDescription().equals(storedModel.getDescription())) {
	                storedModel.setDocumentType(model.getDocumentType());
	            }
	            if (model.getInsertDate() != null && !model.getInsertDate().equals(storedModel.getInsertDate())) {
	                storedModel.setInsertDate(model.getInsertDate());
	            }
	            if (model.getUpdateDate() != null && !model.getUpdateDate().equals(storedModel.getUpdateDate())) {
	                storedModel.setUpdateDate(model.getUpdateDate());
	            }

				storedModel = databaseService.post(storedModel);
			}

			// Garante que qualquer atualização feita na camada de persistência será
			// recuperada.
			storedModel = databaseService.getById(model.getId());

			log.info("Documento atualizado com sucesso!");
			log.debug("storedModel: {}", storedModel);

			return storedModel;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível atualizar o documento.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public Document delete(final UUID id) {
		log.info("Excluindo o documento... ID = {}", id);

		try {
			Document model = databaseService.delete(id);

			log.info("Documento excluído com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível excluir o documento.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public Document getById(final UUID id) {
		log.info("Recuperando o documento... ID = {}", id);

		try {
			Document model = databaseService.getById(id);

			log.info("Documento recuperado com sucesso!");
			log.debug("model: {}", model);

			return model;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar o documento.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public List<Document> getAll() {
		log.info("Recuperando todos os documentos...");

		try {
			List<Document> modelList = databaseService.getAll();

			log.info("Documentos recuperados com sucesso! Quantidade: {}", modelList.size());

			return modelList;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os documentos.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	@Override
	public List<Document> getByBeneficiary(final UUID beneficiaryId) {
		log.info("Recuperando os documentos do beneficiário informado...");
		log.debug("beneficiaryId: {}", beneficiaryId);

		try {
			List<Document> modelList = databaseService.getByBeneficiary(beneficiaryId);

			log.info("Documentos recuperados com sucesso! Quantidade: {}", modelList.size());

			return modelList;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os documentos.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

	// Contagens

	@Override
	public long count() {
		log.info("Contanto os documentos...");

		try {
			long count = databaseService.count();

			log.info("Documentos contados com secusso! Quantidade: {}", count);

			return count;
		} catch (AbstractDatabaseException ex) {
			throw ex;
		} catch (AbstractDomainException ex) {
			throw ex;
		} catch (Exception ex) {
			String message = "Não foi possível contar os documentos.";
			log.error(message, ex);
			throw new InternalServerErrorDomainException(message, ex);
		}
	}

}
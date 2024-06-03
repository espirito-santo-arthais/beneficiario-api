package br.com.ekan.beneficiario.api.domain.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiario.api.domain.exceptions.AbstractDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.InternalServerErrorDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.WarningDomainException;
import br.com.ekan.beneficiario.api.domain.models.Beneficiary;
import br.com.ekan.beneficiario.api.domain.models.Document;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.AbstractDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.services.BeneficiaryDatabaseService;
import br.com.ekan.beneficiario.api.infrastructure.database.services.DocumentDatabaseService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiaryDomainServiceImpl implements BeneficiaryDomainService {

	private final BeneficiaryDatabaseService databaseService;
	private final DocumentDatabaseService documentDatabaseService;

	public BeneficiaryDomainServiceImpl(
			@Lazy BeneficiaryDatabaseService databaseService,
			@Lazy DocumentDatabaseService documentDatabaseService) {
		log.info("Inicializando o serviço...");
		this.databaseService = databaseService;
		this.documentDatabaseService = documentDatabaseService;
	}

	@PostConstruct
	public void postConstruct() {
		log.info("Serviço inicializado com sucesso!...");
	}

	@Transactional
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
			// Extrai a lista de documentos
			List<Document> documentList = model.getDocumentList();
			
			// Desassocia a lista de documentos do modelo inicial
			model.setDocumentList(null);
			
			// Salva o beneficiário sem os documentos
			Beneficiary createdModel = databaseService.post(model);

			// Associa os documentos ao beneficiário criado e salva cada documento.
	        // Depois atualiza a lista de documentos com os documentos salvos.
			final Beneficiary createdModelTemp = createdModel;
	        List<Document> savedDocuments = documentList.stream().map(document -> {
	            document.setBeneficiary(createdModelTemp);
	            return documentDatabaseService.post(document);
	        }).collect(Collectors.toList());
			
			// Garante que qualquer atualização feita na camada de persistência será
			// recuperada.
			createdModel = databaseService.getById(createdModel.getId());
	        
	        // Atualiza o beneficiário com a lista de documentos salvos
	        createdModel.setDocumentList(savedDocuments);

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

	@Transactional
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
			String message = String.format("O atributo %1$s não pode ser diferente do parâmetro %2$s. %1$s = %3$s, %2$s = %4$s", args);
			log.warn(message);
			throw new WarningDomainException(message);
		}

		try {
			Beneficiary storedModel = databaseService.getById(model.getId());
			if (model.hasUpdate()) {
	            if (model.getName() != null && !model.getName().equals(storedModel.getName())) {
	                storedModel.setName(model.getName());
	            }
	            if (model.getPhoneNumber() != null && !model.getPhoneNumber().equals(storedModel.getPhoneNumber())) {
	                storedModel.setPhoneNumber(model.getPhoneNumber());
	            }
	            if (model.getBirthDate() != null && !model.getBirthDate().equals(storedModel.getBirthDate())) {
	                storedModel.setBirthDate(model.getBirthDate());
	            }
	            if (model.getInsertDate() != null && !model.getInsertDate().equals(storedModel.getInsertDate())) {
	                storedModel.setInsertDate(model.getInsertDate());
	            }
	            if (model.getUpdateDate() != null && !model.getUpdateDate().equals(storedModel.getUpdateDate())) {
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

	@Transactional
	@Override
	public Beneficiary delete(final UUID id) {
		log.info("Excluindo o beneficiário... ID = {}", id);

		try {
			List<Document> documentList = this.documentDatabaseService.getByBeneficiary(id);
			documentList.forEach(document -> {
				documentDatabaseService.delete(document.getId());
			});
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
			modelList.forEach(modelTemp -> {
				List<Document> documentList = documentDatabaseService.getByBeneficiary(modelTemp.getId());
				documentList.forEach(documentTemp -> {
					documentTemp.setBeneficiary(null);
				});
				modelTemp.setDocumentList(documentList);
			});
			
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

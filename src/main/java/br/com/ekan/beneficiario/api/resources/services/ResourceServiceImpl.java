package br.com.ekan.beneficiario.api.resources.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import br.com.ekan.beneficiario.api.domain.exceptions.AbstractDomainException;
import br.com.ekan.beneficiario.api.domain.exceptions.InternalServerErrorDomainException;
import br.com.ekan.beneficiario.api.domain.models.AbstractModel;
import br.com.ekan.beneficiario.api.domain.services.DomainService;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.AbstractDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.InternalServerErrorDatabaseException;
import br.com.ekan.beneficiario.api.infrastructure.database.exceptions.NotFoundDatabaseException;
import br.com.ekan.beneficiario.api.resources.dtos.requests.AbstractCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.AbstractUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.AbstractResponseDto;
import br.com.ekan.beneficiario.api.resources.enums.MessageTypeEnum;
import br.com.ekan.beneficiario.api.resources.exceptions.InternalServerErrorResourceException;
import br.com.ekan.beneficiario.api.resources.exceptions.WarningResourceException;
import br.com.ekan.beneficiario.api.resources.mappers.ResourceMapper;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;
import br.com.ekan.beneficiario.api.resources.structures.Message;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ResourceServiceImpl<
		CQ extends AbstractCreateRequestDto, 
		UQ extends AbstractUpdateRequestDto,
		P extends AbstractResponseDto,
		PP extends ResourceMapper<CQ, UQ, P, M>,
		M extends AbstractModel,
		D extends DomainService<M>> 
	implements ResourceService<CQ, UQ, P, PP, M, D> {

	protected final ResourceMapper<CQ, UQ, P, M> mapper;
	protected final DomainService<M> domainService;

	public ResourceServiceImpl(
			ResourceMapper<CQ, UQ, P, M> mapper, 
			DomainService<M> domainService) {
		super();
		log.info("Inicializando o serviço...");
		this.mapper = mapper;
		this.domainService = domainService;
	}

	@PostConstruct
	public void postConstruct() {
		log.info("Serviço inicializado com sucesso!...");
	}
	
	public ApiReturn<P> post(CQ request) {
		log.info("Salvando o recurso...");
		log.debug("request: {}", request);

		try {
			M model = mapper.toCreationModel(request);

			model = domainService.post(model);

			P responseDto = mapper.toResponseDto(model);

			String message = "Recurso salvo com sucesso!";
			ApiReturn<P> apiReturn = getApiReturnForData(responseDto, HttpStatus.OK, message);
			
			log.info(message);
			log.debug("apiReturn: {}", apiReturn);
					
			return apiReturn;
		} catch (AbstractDatabaseException | AbstractDomainException ex) {
			ApiReturn<P> apiReturn = getApiReturnForException(null, ex);
			return apiReturn;
		} catch (Exception ex) {
			String message = "Não foi possível salvar o recurso.";
			log.error(message, ex);
			ApiReturn<P> apiReturn = getApiReturnForException(message, ex);
			return apiReturn;
		}
	}

	public ApiReturn<P> patch(UUID id, UQ request) {
		log.info("Atualizando o recurso...");
		log.debug("id: {}", id);
		log.debug("request: {}", request);

		if (request.getId() == null) {
			Object[] args = { "request.id" };
			String message = String.format("O atributo %1s não pode ser nulo.", args);
			log.error(message);
			ApiReturn<P> apiReturn = getApiReturnForException(null, new WarningResourceException(message));
			return apiReturn;
		}
		if (request.getId().compareTo(id) != 0) {
			Object[] args = { "request.id", "id", request.getId(), id };
			String message = String.format("O atributo %1$s não pode ser diferente do parâmetro %2$s. %1$s = %3$s, %2$s = %4$s", args);
			log.error(message);
			ApiReturn<P> apiReturn = getApiReturnForException(null, new WarningResourceException(message));
			return apiReturn;
		}

		try {
			M model = mapper.toUpdateModel(request);

			model = domainService.patch(id, model);

			P responseDto = mapper.toResponseDto(model);

			String message = "Recurso atualizado com sucesso!";
			ApiReturn<P> apiReturn = getApiReturnForData(responseDto, HttpStatus.OK, message);
			
			log.info(message);
			log.debug("apiReturn: {}", apiReturn);
					
			return apiReturn;
		} catch (AbstractDatabaseException | AbstractDomainException ex) {
			ApiReturn<P> apiReturn = getApiReturnForException(null, ex);
			return apiReturn;
		} catch (Exception ex) {
			String message = "Não foi possível atualizar o recurso.";
			log.error(message, ex);
			ApiReturn<P> apiReturn = getApiReturnForException(message, ex);
			return apiReturn;
		}
	}

	public ApiReturn<P> delete(UUID id) {
		log.info("Excluindo o recurso... ID = {}", id);

		try {
			M model = domainService.delete(id);

			P responseDto = mapper.toResponseDto(model);

			String message = "Recurso excluído com sucesso!";
			ApiReturn<P> apiReturn = getApiReturnForData(responseDto, HttpStatus.OK, message);
			
			log.info(message);
			log.debug("apiReturn: {}", apiReturn);
					
			return apiReturn;
		} catch (AbstractDatabaseException | AbstractDomainException ex) {
			ApiReturn<P> apiReturn = getApiReturnForException(null, ex);
			return apiReturn;
		} catch (Exception ex) {
			String message = "Não foi possível excluir o recurso.";
			log.error(message, ex);
			ApiReturn<P> apiReturn = getApiReturnForException(message, ex);
			return apiReturn;
		}
	}

	public ApiReturn<P> getById(UUID id) {
		log.info("Recuperando o recurso... ID = {}", id);

		try {
			M model = domainService.getById(id);
			
			P responseDto = mapper.toResponseDto(model);

			String message = "Recurso recuperado com sucesso!";
			ApiReturn<P> apiReturn = getApiReturnForData(responseDto, HttpStatus.OK, message);
			
			log.info(message);
			log.debug("apiReturn: {}", apiReturn);
					
			return apiReturn;
		} catch (AbstractDatabaseException | AbstractDomainException ex) {
			ApiReturn<P> apiReturn = getApiReturnForException(null, ex);
			return apiReturn;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar o recurso.";
			log.error(message, ex);
			ApiReturn<P> apiReturn = getApiReturnForException(message, ex);
			return apiReturn;
		}
	}

	public ApiReturn<List<P>> getAll() {
		log.info("Recuperando todos os recursos...");

		try {
			List<M> modelList = domainService.getAll();

			List<P> responseDtoList = mapper.toResponseDtoList(modelList);

			String message = String.format("Recursos recuperados com sucesso! Quantidade: %1s", responseDtoList.size());
			ApiReturn<List<P>> apiReturn = getApiReturnForData(responseDtoList, HttpStatus.OK, message);

			log.info(message);

			return apiReturn;
		} catch (AbstractDatabaseException | AbstractDomainException ex) {
			ApiReturn<List<P>> apiReturn = getApiReturnForException(null, ex);
			return apiReturn;
		} catch (Exception ex) {
			String message = "Não foi possível recuperar os recursos.";
			log.error(message, ex);
			ApiReturn<List<P>> apiReturn = getApiReturnForException(message, ex);
			return apiReturn;
		}
	}

	// Métodos protegidos
	
	protected <T> ApiReturn<T> getApiReturnForData(T data, HttpStatus httpStatus, String messageText) {
	    return getApiReturn(data, httpStatus, messageText, null);
	}
	
	protected <T> ApiReturn<T> getApiReturnForException(String messageText, Exception ex) {
	    return getApiReturn(null, null, messageText, ex);
	}

	// Métodos privados
	
	private <T> ApiReturn<T> getApiReturn(T data, HttpStatus httpStatus, String messageText, Exception ex) {
	    log.info("Construindo o objeto de retorno...");

	    if (ex != null) {
	        return handleExceptionCase(messageText, ex);
	    }
	    
	    if (httpStatus != null && !messageText.isBlank()) {
	        MessageTypeEnum messageType = getMessageTypeForHttpStatus(httpStatus, data == null);
	        return handleDataCase(data, httpStatus, messageText, messageType);
	    }

	    String message = "Não foi possível construir o objeto de retorno. Parâmetros inválidos.";
	    log.error(message);
	    throw new InternalServerErrorResourceException(message);
	}

	private MessageTypeEnum getMessageTypeForHttpStatus(HttpStatus status, boolean isDataNull) {
	    if (status == HttpStatus.OK) return isDataNull ? MessageTypeEnum.INFO : MessageTypeEnum.SUCCESS;
	    if (status == HttpStatus.CREATED) return MessageTypeEnum.SUCCESS;
	    if (status == HttpStatus.NO_CONTENT) return MessageTypeEnum.SUCCESS;
	    if (status == HttpStatus.NOT_FOUND) return MessageTypeEnum.WARNING;
	    if (status == HttpStatus.INTERNAL_SERVER_ERROR) return MessageTypeEnum.ERROR;
	    return MessageTypeEnum.ERROR; // Caso padrão
	}
	
	private <T> ApiReturn<T> handleDataCase(T data, HttpStatus httpStatus, String messageText, MessageTypeEnum messageType) {
		Message apiReturnMessage = Message.builder()
				.text(messageText)
				.type(messageType)
				.build();

	    return ApiReturn.<T>builder()
	            .message(apiReturnMessage)
	            .httpStatus(httpStatus)
	            .data(data)
	            .build();
	}
	
	private <T> ApiReturn<T> handleExceptionCase(String messageText, Exception ex) {
	    HttpStatus apiReturnHttpStatus;

	    if (ex instanceof NotFoundDatabaseException) {
	        apiReturnHttpStatus = HttpStatus.NOT_FOUND;
	    } else if (ex instanceof InternalServerErrorDatabaseException) {
	        apiReturnHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    } else if (ex instanceof InternalServerErrorDomainException) {
	        apiReturnHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    } else {
	        // Caso padrão
	        apiReturnHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }

	    Message apiReturnMessage;
	    if (messageText != null && !messageText.isBlank()) {
	    	MessageTypeEnum messageTypeEnum = getMessageTypeForException(ex);
	    	if (messageTypeEnum == MessageTypeEnum.WARNING) {
		    	apiReturnMessage = getMessage(new WarningResourceException(messageText, ex));
	    	} else {
		    	apiReturnMessage = getMessage(new InternalServerErrorResourceException(messageText, ex));
	    	}
	    } else {
	    	apiReturnMessage = getMessage(ex);
	    }
	    return ApiReturn.<T>builder()
	            .message(apiReturnMessage)
	            .httpStatus(apiReturnHttpStatus)
	            .build();
	}
	
	private Message getMessage(Throwable ex) {
	    MessageTypeEnum messageTypeEnum = getMessageTypeForException(ex);

	    Message.MessageBuilder messageBuilder = Message.builder()
	            .text(ex.getMessage())
	            .type(messageTypeEnum);

	    if (ex.getCause() != null) {
	        messageBuilder.causeMessageList(Collections.singletonList(getMessage(ex.getCause())));
	    }
	    if (ex.getSuppressed() != null && ex.getSuppressed().length > 0) {
	        List<Message> suppressedMessages = Arrays.stream(ex.getSuppressed())
	                                                .map(this::getMessage)
	                                                .collect(Collectors.toList());
	        messageBuilder.causeMessageList(suppressedMessages);
	    }

	    return messageBuilder.build();
	}

	private MessageTypeEnum getMessageTypeForException(Throwable ex) {
		if (ex instanceof NotFoundDatabaseException) {
	        return MessageTypeEnum.WARNING;
	    } else if (ex instanceof InternalServerErrorDatabaseException) {
	        return MessageTypeEnum.ERROR;
	    } else if (ex instanceof InternalServerErrorDomainException) {
	        return MessageTypeEnum.ERROR;
	    }
	    return MessageTypeEnum.ERROR;
	}

}
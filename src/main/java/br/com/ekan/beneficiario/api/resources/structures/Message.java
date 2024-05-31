package br.com.ekan.beneficiario.api.resources.structures;

import java.util.List;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ekan.beneficiario.api.resources.enums.MessageTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/*
 * Classe que retorna um mensagem para o cliente da API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclui propriedades nulas na serialização
public class Message {

	/**
	 * Tipo da mensagem (ERRO, INFORMAÇÃO, AVISO, SUCESSO).
	 */
	@NotNull(message = "Não pode ser nulo")
	private MessageTypeEnum type;

	/**
	 * Texto da mensagem.
	 */
	@NotBlank(message = "Não pode ser nulo ou vazio")
	private String text;

	/**
	 * Causas aninhadas, principalmente para erros.
	 */
	@Nullable
	@Singular("causeMessage")
	private List<
		@NotBlank(message = "Não pode ser nulo ou vazio") 
		Message> causeMessageList;

}
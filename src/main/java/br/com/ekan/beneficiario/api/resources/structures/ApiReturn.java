package br.com.ekan.beneficiario.api.resources.structures;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * Classe Base para a Resposta da API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclui propriedades nulas na serialização
public class ApiReturn<P> {

	/**
	 * Código de status HTTP da resposta.
	 */
	@NotNull(message = "Não pode ser nulo")
	private HttpStatus httpStatus;

	/**
	 * Pode ser um objeto, uma lista de objetos ou um Page.
	 */
	@Nullable
	private P data;

	/**
	 * Lista de mensagens (erro, informação, etc.)
	 */
	@NotNull(message = "Não pode ser nulo")
	@Singular("message")
	private List<@NotBlank(message = "Não pode ser nulo ou vazio") Message> messageList;

	/**
	 * Tamanho da lista, se aplicável.
	 */
	@Nullable
	@Min(0)
	private Integer size;
}

package br.com.ekan.beneficiario.api.resources.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryCreateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.requests.BeneficiaryUpdateRequestDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.BeneficiaryResponseDto;
import br.com.ekan.beneficiario.api.resources.dtos.responses.DocumentResponseDto;
import br.com.ekan.beneficiario.api.resources.structures.ApiReturn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Endpoints para manutenção e recuperação de beneficiários.
 *
 * Aqui também estão os códigos de status HTTP possíveis que podem ser
 * retornados.
 *
 * Nos casos onde um endpoint específico não seja aplicável, a implementação
 * deve retornar um status 501 (Not Implemented).
 *
 * <ul>
 * <li><b>200 OK:</b> A requisição foi bem-sucedida e a resposta contém os dados esperados.</li>
 * <li><b>201 Created:</b> Um recurso foi criado com sucesso. Utilizado em respostas a requisições POST que resultam na criação de um novo recurso.</li>
 * <li><b>400 Bad Request:</b> A requisição não pode ser processada devido a um erro do cliente, como dados de entrada malformados ou parâmetros inválidos.</li>
 * <li><b>401 Unauthorized:</b> Falha na autenticação. Indica que o usuário precisa se autenticar para acessar o recurso.</li>
 * <li><b>403 Forbidden:</b> O usuário autenticado não tem permissão para acessar o recurso especificado.</li>
 * <li><b>404 Not Found:</b> O recurso solicitado não foi encontrado. Utilizado quando um endpoint é válido, mas o recurso específico não existe.</li>
 * <li><b>405 Method Not Allowed:</b> O método HTTP usado na requisição não é suportado pelo endpoint.</li>
 * <li><b>406 Not Acceptable:</b> O recurso solicitado não pode ser fornecido no formato especificado pelo cabeçalho 'Accept' da requisição.</li>
 * <li><b>415 Unsupported Media Type:</b> O tipo de mídia dos dados da requisição não é suportado pelo servidor.</li>
 * <li><b>422 Unprocessable Entity:</b> A requisição foi bem formada, mas não foi possível processá-la devido a erros semânticos.</li>
 * <li><b>500 Internal Server Error:</b> Erro genérico indicando uma falha no servidor.</li>
 * <li><b>501 Not Implemented:</b> O servidor não suporta a funcionalidade requerida para atender a requisição.</li>
 * <li><b>502 Bad Gateway:</b> O servidor atuando como gateway ou proxy recebeu uma resposta inválida do servidor upstream.</li>
 * <li><b>503 Service Unavailable:</b> O servidor não está pronto para manipular a requisição, geralmente devido a sobrecarga ou manutenção.</li>
 * </ul>
 * 
 * @param <P> Extends AbstractResponseDTO
 */
@RestController
@RequestMapping("/beneficiarios")
@Tag(
		name = "Beneficiários", 
		description = "Endpoints para manutenção e recuperação de beneficiários.")
@Validated
@ApiResponses(value = { 
		@ApiResponse(responseCode = "400", description = "Bad Request (Erro na Requisição): A requisição não pode ser processada devido a um erro do cliente, como dados de entrada malformados ou parâmetros inválidos."),
		@ApiResponse(responseCode = "401", description = "Unauthorized (Não Autorizado): Falha na autenticação. Indica que o usuário precisa se autenticar para acessar o recurso."),
		@ApiResponse(responseCode = "403", description = "Forbidden (Proibido): O usuário autenticado não tem permissão para acessar o recurso especificado."),
		@ApiResponse(responseCode = "405", description = "Method Not Allowed (Método Não Permitido): O método HTTP usado na requisição não é suportado pelo endpoint."),
		@ApiResponse(responseCode = "406", description = "Not Acceptable (Não Aceitável): O recurso solicitado não pode ser fornecido no formato especificado pelo cabeçalho 'Accept' da requisição."),
		@ApiResponse(responseCode = "415", description = "Unsupported Media Type (Tipo de Mídia Não Suportada): O tipo de mídia dos dados da requisição não é suportado pelo servidor."),
		@ApiResponse(responseCode = "422", description = "Unprocessable Entity (Entidade Não Processável): A requisição foi bem formada, mas não foi possível processá-la devido a erros semânticos."),
		@ApiResponse(responseCode = "500", description = "Internal Server Error (Erro Interno de Servidor): "),
		@ApiResponse(responseCode = "501", description = "Not Implemented (Não Implementado): Erro genérico indicando uma falha no servidor."),
		@ApiResponse(responseCode = "502", description = "Bad Gateway (Erro no Gateway): O servidor atuando como gateway ou proxy recebeu uma resposta inválida do servidor upstream."),
		@ApiResponse(responseCode = "503", description = "Service Unavailable (Serviço Indisponível): O servidor não está pronto para manipular a requisição, geralmente devido a sobrecarga ou manutenção.") })
public interface BeneficiarioApi {

	@PostMapping
	@Operation(
			summary = "Criar beneficiário",
			description = "Cria um novo beneficiário.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Created (Criado): Um recurso foi criado com sucesso. Utilizado em respostas a requisições POST que resultam na criação de um novo recurso.") })
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<ApiReturn<BeneficiaryResponseDto>> post( 
			@Valid
			@NotNull(message = "Não pode ser nulo")
			@RequestBody(required = true)
			@Parameter(
					description = "DTO para envio de requisição", 
					required = true)
			BeneficiaryCreateRequestDto createRequest);

	@PatchMapping(value = "/{id}")
	@Operation(
			summary = "Atualizar beneficiário", 
			description = "Atualiza um beneficiário existente.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK (Sucesso): A requisição foi bem-sucedida e a resposta contém os dados esperados."),
			@ApiResponse(responseCode = "404", description = "Not Found (Não Encontrado) : O recurso solicitado não foi encontrado. Utilizado quando um endpoint é válido, mas o recurso específico não existe.") })
	ResponseEntity<ApiReturn<BeneficiaryResponseDto>> patch(
			@NotNull(message = "Não pode ser nulo")
			@PathVariable(name = "id", required = true)
			@Parameter(
					description = "ID do recurso",
					required = true,
					example = "970fe566-66f8-4d01-a873-d8100049e06d")
			UUID id, 
			@Valid
			@NotNull(message = "Não pode ser nulo")
			@RequestBody(required = true)
			@Parameter(
					description = "DTO para envio de requisição", 
					required = true)
			BeneficiaryUpdateRequestDto updateRequest);

	@DeleteMapping("/{id}")
	@Operation(
			summary = "Excluir beneficiário",
			description = "Exclui um beneficiário.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK (Sucesso): A requisição foi bem-sucedida e a resposta contém os dados esperados."),
			@ApiResponse(responseCode = "404", description = "Not Found (Não Encontrado) : O recurso solicitado não foi encontrado. Utilizado quando um endpoint é válido, mas o recurso específico não existe.") })
	ResponseEntity<ApiReturn<BeneficiaryResponseDto>> delete(
			@NotNull(message = "Não pode ser nulo")
			@PathVariable(name = "id", required = true)
			@Parameter(
					description = "ID do recurso",
					required = true,
					example = "970fe566-66f8-4d01-a873-d8100049e06d")
			UUID id);

	@GetMapping
	@Operation(
			summary = "Recuperar beneficiários", 
			description = "Recupera todos os beneficiários existentes.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK (Sucesso): A requisição foi bem-sucedida e a resposta contém os dados esperados.") })
	ResponseEntity<ApiReturn<List<BeneficiaryResponseDto>>> getAll();

	@GetMapping("/{id}/documentos")
	@Operation(
			summary = "Recuperar documentos", 
			description = "Recupera todos os documentos de um beneficiário existente.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK (Sucesso): A requisição foi bem-sucedida e a resposta contém os dados esperados.") })
	ResponseEntity<ApiReturn<List<DocumentResponseDto>>> getDocumentosByBeneficiarioId(
			@NotNull(message = "Não pode ser nulo")
			@PathVariable(name = "id", required = true)
			@Parameter(
					description = "ID do recurso",
					required = true,
					example = "970fe566-66f8-4d01-a873-d8100049e06d")
			UUID id);

}

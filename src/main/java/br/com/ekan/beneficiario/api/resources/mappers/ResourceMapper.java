package br.com.ekan.beneficiario.api.resources.mappers;

import java.util.List;

/**
 * Interface para os mapeadores da camada Resources.
 * 
 * @param <CQ> Classe extentendo de AbstractCreationRequestDto
 * @param <UQ> Classe extentendo de AbstractUpdateRequestDto
 * @param <P>  Classe extentendo de AbstractResponseDto
 * @param <M>  Classe extentendo de AbstractModel
 */
public interface ResourceMapper<CQ, UQ, P, M> {

	M toCreationModel(CQ request);

	M toUpdateModel(UQ request);

	List<M> toCreationModelList(List<CQ> requests);

	List<M> toUpdateModelList(List<UQ> requests);

	P toResponseDto(M model);

	List<P> toResponseDtoList(List<M> model);

}

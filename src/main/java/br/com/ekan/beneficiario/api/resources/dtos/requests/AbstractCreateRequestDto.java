package br.com.ekan.beneficiario.api.resources.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractCreateRequestDto extends AbstractRequestDto {

}
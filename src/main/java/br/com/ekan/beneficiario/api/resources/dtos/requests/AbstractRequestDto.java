package br.com.ekan.beneficiario.api.resources.dtos.requests;

import br.com.ekan.beneficiario.api.resources.dtos.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractRequestDto extends AbstractDto {

}
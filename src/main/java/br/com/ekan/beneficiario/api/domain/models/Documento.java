package br.com.ekan.beneficiario.api.domain.models;

import org.springframework.lang.Nullable;

import br.com.ekan.beneficiario.api.domain.enums.TipoDocumentoEnum;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Documento extends AbstractModel {

	@Nullable
	@EqualsAndHashCode.Include
	private TipoDocumentoEnum tipoDocumentoEnum;

	@Nullable
	@Size(	min = 1,
			max = 100,
			message = "Deve ter entre 1 e 100 caracteres")
	private String descricao;

	public boolean hasUpdate() {
		return this.tipoDocumentoEnum != null 
				|| this.descricao != null;
	}

}

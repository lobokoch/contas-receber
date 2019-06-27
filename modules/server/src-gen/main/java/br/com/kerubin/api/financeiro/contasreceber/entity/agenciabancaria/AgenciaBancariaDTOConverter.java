/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.contasreceber.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.agenciabancaria.AgenciaBancariaEvent;

@Component
public class AgenciaBancariaDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public AgenciaBancaria convertEntityToDto(AgenciaBancariaEntity entity) {
		AgenciaBancaria dto = null;
		if (entity != null) {
			dto = mapper.map(entity, AgenciaBancaria.class);
		}
		return dto;
	}


	public AgenciaBancariaEntity convertDtoToEntity(AgenciaBancaria dto) {
		AgenciaBancariaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, AgenciaBancariaEntity.class);
		}
		return entity;
	}


	public AgenciaBancariaEntity convertDtoToEntity(AgenciaBancariaEvent dto) {
		AgenciaBancariaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, AgenciaBancariaEntity.class);
		}
		return entity;
	}


}
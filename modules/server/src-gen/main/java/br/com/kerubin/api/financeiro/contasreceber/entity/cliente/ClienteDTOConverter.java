/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;
import br.com.kerubin.api.cadastros.cliente.entity.cliente.ClienteEvent;

@Component
public class ClienteDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public Cliente convertEntityToDto(ClienteEntity entity) {
		Cliente dto = null;
		if (entity != null) {
			dto = mapper.map(entity, Cliente.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public ClienteEntity convertDtoToEntity(Cliente dto) {
		ClienteEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ClienteEntity.class);
		}
		return entity;
	}


	public ClienteEntity convertDtoToEntity(ClienteEvent dto) {
		ClienteEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ClienteEntity.class);
		}
		return entity;
	}


}
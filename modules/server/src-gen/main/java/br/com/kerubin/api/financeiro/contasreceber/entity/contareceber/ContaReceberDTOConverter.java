/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:19.082
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class ContaReceberDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public ContaReceber convertEntityToDto(ContaReceberEntity entity) {
		ContaReceber dto = null;
		if (entity != null) {
			dto = mapper.map(entity, ContaReceber.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public ContaReceberEntity convertDtoToEntity(ContaReceber dto) {
		ContaReceberEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ContaReceberEntity.class);
		}
		return entity;
	}


}
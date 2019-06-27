/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.contasreceber.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.bandeiracartao.BandeiraCartaoEvent;

@Component
public class BandeiraCartaoDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public BandeiraCartao convertEntityToDto(BandeiraCartaoEntity entity) {
		BandeiraCartao dto = null;
		if (entity != null) {
			dto = mapper.map(entity, BandeiraCartao.class);
		}
		return dto;
	}


	public BandeiraCartaoEntity convertDtoToEntity(BandeiraCartao dto) {
		BandeiraCartaoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, BandeiraCartaoEntity.class);
		}
		return entity;
	}


	public BandeiraCartaoEntity convertDtoToEntity(BandeiraCartaoEvent dto) {
		BandeiraCartaoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, BandeiraCartaoEntity.class);
		}
		return entity;
	}


}
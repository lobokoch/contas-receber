/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.contasreceber.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.cartaocredito.CartaoCreditoEvent;

@Component
public class CartaoCreditoDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public CartaoCredito convertEntityToDto(CartaoCreditoEntity entity) {
		CartaoCredito dto = null;
		if (entity != null) {
			dto = mapper.map(entity, CartaoCredito.class);
		}
		return dto;
	}


	public CartaoCreditoEntity convertDtoToEntity(CartaoCredito dto) {
		CartaoCreditoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CartaoCreditoEntity.class);
		}
		return entity;
	}


	public CartaoCreditoEntity convertDtoToEntity(CartaoCreditoEvent dto) {
		CartaoCreditoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CartaoCreditoEntity.class);
		}
		return entity;
	}


}
/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;


public interface ClienteService {
	
	public ClienteEntity create(ClienteEntity clienteEntity);
	
	public ClienteEntity read(java.util.UUID id);
	
	public ClienteEntity update(java.util.UUID id, ClienteEntity clienteEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ClienteEntity> list(ClienteListFilter clienteListFilter, Pageable pageable);
	
	public Collection<ClienteAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<ClienteNomeAutoComplete> clienteNomeAutoComplete(String query);
}

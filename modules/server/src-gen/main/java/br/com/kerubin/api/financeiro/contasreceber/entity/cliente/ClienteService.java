/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteService {
	
	public ClienteEntity create(ClienteEntity clienteEntity);
	
	public ClienteEntity read(java.util.UUID id);
	
	public ClienteEntity update(java.util.UUID id, ClienteEntity clienteEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ClienteEntity> list(ClienteListFilter clienteListFilter, Pageable pageable);
	
	public Collection<ClienteAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<ClienteNomeAutoComplete> clienteNomeAutoComplete(String query);
}

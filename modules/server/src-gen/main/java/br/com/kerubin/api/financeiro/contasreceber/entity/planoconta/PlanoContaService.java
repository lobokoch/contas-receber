/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.planoconta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;


public interface PlanoContaService {
	
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity);
	
	public PlanoContaEntity read(java.util.UUID id);
	
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable);
	
	public Collection<PlanoContaAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<PlanoContaAutoComplete> planoContaPlanoContaPaiAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query);
	
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query);
}

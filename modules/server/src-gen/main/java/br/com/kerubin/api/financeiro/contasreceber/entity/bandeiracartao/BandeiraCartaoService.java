/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BandeiraCartaoService {
	
	public BandeiraCartaoEntity create(BandeiraCartaoEntity bandeiraCartaoEntity);
	
	public BandeiraCartaoEntity read(java.util.UUID id);
	
	public BandeiraCartaoEntity update(java.util.UUID id, BandeiraCartaoEntity bandeiraCartaoEntity);
	
	public void delete(java.util.UUID id);
	
	public void deleteInBulk(java.util.List<java.util.UUID> idList);
	
	public Page<BandeiraCartaoEntity> list(BandeiraCartaoListFilter bandeiraCartaoListFilter, Pageable pageable);
	
	public Collection<BandeiraCartaoAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<BandeiraCartaoNomeBandeiraAutoComplete> bandeiraCartaoNomeBandeiraAutoComplete(String query);
}

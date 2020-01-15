/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:19.082
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteAutoComplete;

public interface ContaReceberService {
	
	public ContaReceberEntity create(ContaReceberEntity contaReceberEntity);
	
	public ContaReceberEntity read(java.util.UUID id);
	
	public ContaReceberEntity update(java.util.UUID id, ContaReceberEntity contaReceberEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ContaReceberEntity> list(ContaReceberListFilter contaReceberListFilter, Pageable pageable);
	
	public Collection<ContaReceberAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query);
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query);
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query);
	public Collection<ClienteAutoComplete> clienteClienteAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<ContaReceberDescricaoAutoComplete> contaReceberDescricaoAutoComplete(String query);
	
	public Collection<ContaReceberHistConcBancariaAutoComplete> contaReceberHistConcBancariaAutoComplete(String query);
	
	public Collection<ContaReceberAgrupadorAutoComplete> contaReceberAgrupadorAutoComplete(String query);
	
	public ContaReceberSumFields getContaReceberSumFields(ContaReceberListFilter contaReceberListFilter);
	
	public void actionBaixarContaComDataPagamentoHoje(java.util.UUID id);
	
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(java.util.UUID id);
	
	public void actionEstornarRecebimentoContaComUmClique(java.util.UUID id);
	
	public void actionFazerCopiasContaReceber(ContaReceberMakeCopies contaReceberMakeCopies);
}
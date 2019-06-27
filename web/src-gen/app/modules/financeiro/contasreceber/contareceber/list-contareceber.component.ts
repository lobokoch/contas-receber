/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.789
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Component, OnInit } from '@angular/core';
import {ConfirmationService, LazyLoadEvent, SelectItem} from 'primeng/api';
import { Dropdown } from 'primeng/dropdown';
import * as moment from 'moment';
import { MessageHandlerService } from 'src/app/core/message-handler.service';

import { ContaReceberService } from './contareceber.service';
import { FinanceiroContasReceberTranslationService } from './../i18n/financeiro-contasreceber-translation.service';
import { ContaReceber } from './contareceber.model';
import { ContaReceberListFilter } from './contareceber.model';
import { SortField } from './contareceber.model';
import { ContaReceberDescricaoAutoComplete } from './contareceber.model';
import { ContaReceberAgrupadorAutoComplete } from './contareceber.model';

import { PlanoContaAutoComplete } from './../planoconta/planoconta.model';

import { ContaBancariaAutoComplete } from './../contabancaria/contabancaria.model';

import { CartaoCreditoAutoComplete } from './../cartaocredito/cartaocredito.model';

import { ClienteAutoComplete } from './../cliente/cliente.model';
import { ContaReceberSumFields } from './contareceber.model';

@Component({
  selector: 'app-list-contareceber.component',
  templateUrl: './list-contareceber.component.html',
  styleUrls: ['./list-contareceber.component.css']
})

export class ContaReceberListComponent implements OnInit {
	
	contaReceberListItems: ContaReceber[];
	contaReceberListTotalElements = 0;
	contaReceberListFilter = new ContaReceberListFilter();
	
	contaReceberDescricaoAutoCompleteSuggestions: ContaReceberDescricaoAutoComplete[];
	
	contaReceberDataVencimentoIsBetweenOptionsSelected: SelectItem = {label: 'Minha competência', value: '12'};
	
	
	
	
	contaReceberAgrupadorAutoCompleteSuggestions: ContaReceberAgrupadorAutoComplete[];
	dateFilterIntervalDropdownItems: SelectItem[];
	
	contaReceberSumFields = new ContaReceberSumFields();
	
	constructor(
	    private contaReceberService: ContaReceberService,
	    private financeiroContasReceberTranslationService: FinanceiroContasReceberTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
		this.contaReceberDataVencimentoIsBetweenOptionsOnClick(null);
		this.initializeDateFilterIntervalDropdownItems();
		
		
		
		this.contaReceberListFilter.dataPagamentoIsNotNull = false;
		
		this.contaReceberListFilter.dataPagamentoIsNull = true;
		
	}
	
	contaReceberList(pageNumber = 0) {
	    this.contaReceberListFilter.pageNumber = pageNumber;
	    this.contaReceberService
	    .contaReceberList(this.contaReceberListFilter)
	    .then(result => {
	      	this.contaReceberListItems = result.items;
	      	this.contaReceberListTotalElements = result.totalElements;
	      
			this.getContaReceberSumFields();
	    });
		
	}
	
	getContaReceberSumFields() {
	    this.contaReceberService.getContaReceberSumFields(this.contaReceberListFilter)
		.then(response => {
		  this.contaReceberSumFields = response;
		})
		.catch(error => {
		  this.messageHandler.showError('Erro ao buscar totais:' + error);
		});
	}
	
	contaReceberFilterSearch() {
	    this.contaReceberList(0);
	}
	
	deleteContaReceber(contaReceber: ContaReceber) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.contaReceberService.delete(contaReceber.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.contaReceberList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	contaReceberListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.contaReceberListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.contaReceberListFilter.sortField = new SortField('dataVencimento', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.contaReceberList(pageNumber);
	}
	
	contaReceberDescricaoAutoComplete(event) {
	    const query = event.query;
	    this.contaReceberService.contaReceberDescricaoAutoComplete(query)
	    .then((result) => {
	      this.contaReceberDescricaoAutoCompleteSuggestions = result;
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao buscar registros com o termo: ' + query);
	    });
	}
	
	contaReceberAgrupadorAutoComplete(event) {
	    const query = event.query;
	    this.contaReceberService.contaReceberAgrupadorAutoComplete(query)
	    .then((result) => {
	      this.contaReceberAgrupadorAutoCompleteSuggestions = result;
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao buscar registros com o termo: ' + query);
	    });
	}
	
	
	contaReceberPlanoContasAutoCompleteFieldConverter(planoContas: PlanoContaAutoComplete) {
		if (planoContas) {
			return (planoContas.codigo || '<nulo>') + ' - ' + (planoContas.descricao || '<nulo>');
		} else {
			return null;
		}
	}
	
	contaReceberContaBancariaAutoCompleteFieldConverter(contaBancaria: ContaBancariaAutoComplete) {
		if (contaBancaria) {
			return (contaBancaria.nomeTitular || '<nulo>') + ' - ' + (contaBancaria.numeroConta || '<nulo>');
		} else {
			return null;
		}
	}
	
	contaReceberCartaoCreditoAutoCompleteFieldConverter(cartaoCredito: CartaoCreditoAutoComplete) {
		if (cartaoCredito) {
			return (cartaoCredito.nomeTitular || '<nulo>') + ' - ' + (cartaoCredito.numeroCartao || '<nulo>');
		} else {
			return null;
		}
	}
	
	contaReceberClienteAutoCompleteFieldConverter(cliente: ClienteAutoComplete) {
		if (cliente) {
			return (cliente.nome || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	private initializeDateFilterIntervalDropdownItems() {
		this.dateFilterIntervalDropdownItems = [
		    {label: 'Minha competência', value: '12'},
		    {label: 'Hoje', value: '0'},
		    {label: 'Amanhã', value: '1'},
		    {label: 'Esta semana', value: '2'},
		    {label: 'Semana que vem', value: '3'},
		    {label: 'Este mês', value: '4'},
		    {label: 'Mês que vem', value: '5'},
		    {label: 'Este ano', value: '6'},
		    {label: 'Ano que vem', value: '7'},
		    // Passado
		    {label: 'Ontem', value: '8'},
		    {label: 'Semana passada', value: '9'},
		    {label: 'Mês passado', value: '10'},
		    {label: 'Ano passado', value: '11'},
		    {label: 'Personalizado', value: '99'}
		  ];
	}
	
	
	contaReceberDataVencimentoIsBetweenOptionsOnClick(dropdown: Dropdown) {
		this.contaReceberListFilter.dataVencimentoFrom = null;
		this.contaReceberListFilter.dataVencimentoTo = null;
		
		let dateFrom = null;
		let dateTo = null;
	
		const valor = Number(this.contaReceberDataVencimentoIsBetweenOptionsSelected.value);
		switch (valor) {
			case 0: // Hoje
				dateFrom = moment();
				dateTo = moment();
				break;
				//
			case 1: // Amanhã
				dateFrom = moment().add(1, 'day');
				dateTo = moment().add(1, 'day');
				break;
				//
			case 2: // Esta semana
				dateFrom = moment().startOf('week');
				dateTo = moment().endOf('week');
				break;
				//
			case 3: // Semana que vem
				dateFrom = moment().add(1, 'week').startOf('week');
				dateTo = moment().add(1, 'week').endOf('week');
				break;
				//
			case 4: // Este mês
				dateFrom = moment().startOf('month');
				dateTo = moment().endOf('month');
				break;
				//
			case 5: // Mês que vem
				dateFrom = moment().add(1, 'month').startOf('month');
				dateTo = moment().add(1, 'month').endOf('month');
				break;
				//
			case 6: // Este ano
				dateFrom = moment().startOf('year');
				dateTo = moment().endOf('year');
				break;
				//
			case 7: // Ano que vem
				dateFrom = moment().add(1, 'year').startOf('year');
				dateTo = moment().add(1, 'year').endOf('year');
				break;
				// Passado
			case 8: // Ontem
				dateFrom = moment().add(-1, 'day');
				dateTo = moment().add(-1, 'day');
				break;
				//
			case 9: // Semana passada
				dateFrom = moment().add(-1, 'week').startOf('week');
				dateTo = moment().add(-1, 'week').endOf('week');
				break;
				//
			case 10: // Mês passado
				dateFrom = moment().add(-1, 'month').startOf('month');
				dateTo = moment().add(-1, 'month').endOf('month');
				break;
				//
			case 11: // Ano passado
				dateFrom = moment().add(-1, 'year').startOf('year');
				dateTo = moment().add(-1, 'year').endOf('year');
				break;
				
			case 12: // Minha competência
				dateFrom = moment().startOf('month');
				dateTo = moment().endOf('month').add(5, 'day'); // Five days after and of the month
				break;
			
			default:
				break;
		} // switch
	
		if (dateFrom != null) {
		  this.contaReceberListFilter.dataVencimentoFrom = dateFrom.toDate();
		}
		
		if (dateTo != null) {
		  this.contaReceberListFilter.dataVencimentoTo = dateTo.toDate();
		}
		
		if (dateFrom != null && dateTo != null) {
		  // this.contaReceberList(0);
		}
	}
	
	applyAndGetRuleGridRowStyleClass(contaReceber: ContaReceber): String {
		
		if (!contaReceber.dataPagamento && moment(contaReceber.dataVencimento).isBefore(moment(), 'day')) {
			return 'kb-conta-vencida';
		}
		
		if (!contaReceber.dataPagamento && moment(contaReceber.dataVencimento).isSame(moment(), 'day')) {
			return 'kb-conta-vence-hoje';
		}
		
		if (!contaReceber.dataPagamento && moment(contaReceber.dataVencimento).isSame(moment().add(1, 'day'), 'day')) {
			return 'kb-conta-vence-amanha';
		}
		
		if (!contaReceber.dataPagamento && moment(contaReceber.dataVencimento).isBetween(moment(), moment().add(3, 'day'))) {
			return 'kb-conta-vence-proximos-3-dias';
		}
		
		if (!contaReceber.dataPagamento && moment(contaReceber.dataVencimento).isBetween(moment(), moment().endOf('week'))) {
			return 'kb-conta-vence-esta-semana';
		}
		
		if (contaReceber.dataPagamento) {
			return 'kb-conta-paga';
		}
	
	    return null;
	}
	
	actionBaixarContaComUmCliqueWhen(contaReceber: ContaReceber) {
		return !contaReceber.dataPagamento;
	}
	
	actionBaixarContaComUmClique(contaReceber: ContaReceber) {
		this.contaReceberService.actionBaixarContaComUmClique(contaReceber.id)
			.then(() => {
			  this.messageHandler.showSuccess('Ação executada com sucesso!');
			  this.contaReceberList(0);
			})
			.catch((e) => {
				console.log('Erro ao executar a ação actionBaixarContaComUmClique: ' + e);
			  	this.messageHandler.showError('Não foi possível executar a ação.');
			});
	}
	
	actionEstornarRecebimentoContaComUmCliqueWhen(contaReceber: ContaReceber) {
		return contaReceber.dataPagamento;
	}
	
	actionEstornarRecebimentoContaComUmClique(contaReceber: ContaReceber) {
		this.contaReceberService.actionEstornarRecebimentoContaComUmClique(contaReceber.id)
			.then(() => {
			  this.messageHandler.showSuccess('Ação executada com sucesso!');
			  this.contaReceberList(0);
			})
			.catch((e) => {
				console.log('Erro ao executar a ação actionEstornarRecebimentoContaComUmClique: ' + e);
			  	this.messageHandler.showError('Não foi possível executar a ação.');
			});
	}
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroContasReceberTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
	
	
}

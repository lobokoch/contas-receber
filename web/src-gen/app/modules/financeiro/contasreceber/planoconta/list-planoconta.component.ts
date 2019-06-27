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

import { PlanoContaService } from './planoconta.service';
import { FinanceiroContasReceberTranslationService } from './../i18n/financeiro-contasreceber-translation.service';
import { PlanoConta } from './planoconta.model';
import { PlanoContaListFilter } from './planoconta.model';
import { SortField } from './planoconta.model';
import { PlanoContaCodigoAutoComplete } from './planoconta.model';
import { PlanoContaDescricaoAutoComplete } from './planoconta.model';

import { PlanoContaAutoComplete } from './../planoconta/planoconta.model';

@Component({
  selector: 'app-list-planoconta.component',
  templateUrl: './list-planoconta.component.html',
  styleUrls: ['./list-planoconta.component.css']
})

export class PlanoContaListComponent implements OnInit {
	
	planoContaListItems: PlanoConta[];
	planoContaListTotalElements = 0;
	planoContaListFilter = new PlanoContaListFilter();
	
	planoContaCodigoAutoCompleteSuggestions: PlanoContaCodigoAutoComplete[];
	planoContaDescricaoAutoCompleteSuggestions: PlanoContaDescricaoAutoComplete[];
	
	
	
	dateFilterIntervalDropdownItems: SelectItem[];
	
	
	constructor(
	    private planoContaService: PlanoContaService,
	    private financeiroContasReceberTranslationService: FinanceiroContasReceberTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
		
		this.planoContaListFilter.ativoIsNotNull = true;
		
		this.planoContaListFilter.ativoIsNull = false;
		
	}
	
	planoContaList(pageNumber = 0) {
	    this.planoContaListFilter.pageNumber = pageNumber;
	    this.planoContaService
	    .planoContaList(this.planoContaListFilter)
	    .then(result => {
	      	this.planoContaListItems = result.items;
	      	this.planoContaListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	planoContaFilterSearch() {
	    this.planoContaList(0);
	}
	
	deletePlanoConta(planoConta: PlanoConta) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.planoContaService.delete(planoConta.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.planoContaList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	planoContaListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.planoContaListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.planoContaListFilter.sortField = new SortField('id', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.planoContaList(pageNumber);
	}
	
	planoContaCodigoAutoComplete(event) {
	    const query = event.query;
	    this.planoContaService.planoContaCodigoAutoComplete(query)
	    .then((result) => {
	      this.planoContaCodigoAutoCompleteSuggestions = result;
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao buscar registros com o termo: ' + query);
	    });
	}
	
	planoContaDescricaoAutoComplete(event) {
	    const query = event.query;
	    this.planoContaService.planoContaDescricaoAutoComplete(query)
	    .then((result) => {
	      this.planoContaDescricaoAutoCompleteSuggestions = result;
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao buscar registros com o termo: ' + query);
	    });
	}
	
	
	planoContaPlanoContaPaiAutoCompleteFieldConverter(planoContaPai: PlanoContaAutoComplete) {
		if (planoContaPai) {
			return (planoContaPai.codigo || '<nulo>') + ' - ' + (planoContaPai.descricao || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroContasReceberTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
	
	
}

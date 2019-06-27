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

import { ContaBancariaService } from './contabancaria.service';
import { FinanceiroContasReceberTranslationService } from './../i18n/financeiro-contasreceber-translation.service';
import { ContaBancaria } from './contabancaria.model';
import { ContaBancariaListFilter } from './contabancaria.model';
import { SortField } from './contabancaria.model';
import { ContaBancariaNumeroContaAutoComplete } from './contabancaria.model';

import { AgenciaBancariaAutoComplete } from './../agenciabancaria/agenciabancaria.model';

@Component({
  selector: 'app-list-contabancaria.component',
  templateUrl: './list-contabancaria.component.html',
  styleUrls: ['./list-contabancaria.component.css']
})

export class ContaBancariaListComponent implements OnInit {
	
	contaBancariaListItems: ContaBancaria[];
	contaBancariaListTotalElements = 0;
	contaBancariaListFilter = new ContaBancariaListFilter();
	
	contaBancariaNumeroContaAutoCompleteSuggestions: ContaBancariaNumeroContaAutoComplete[];
	dateFilterIntervalDropdownItems: SelectItem[];
	
	
	constructor(
	    private contaBancariaService: ContaBancariaService,
	    private financeiroContasReceberTranslationService: FinanceiroContasReceberTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
	}
	
	contaBancariaList(pageNumber = 0) {
	    this.contaBancariaListFilter.pageNumber = pageNumber;
	    this.contaBancariaService
	    .contaBancariaList(this.contaBancariaListFilter)
	    .then(result => {
	      	this.contaBancariaListItems = result.items;
	      	this.contaBancariaListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	contaBancariaFilterSearch() {
	    this.contaBancariaList(0);
	}
	
	deleteContaBancaria(contaBancaria: ContaBancaria) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.contaBancariaService.delete(contaBancaria.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.contaBancariaList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	contaBancariaListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.contaBancariaListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.contaBancariaListFilter.sortField = new SortField('id', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.contaBancariaList(pageNumber);
	}
	
	contaBancariaNumeroContaAutoComplete(event) {
	    const query = event.query;
	    this.contaBancariaService.contaBancariaNumeroContaAutoComplete(query)
	    .then((result) => {
	      this.contaBancariaNumeroContaAutoCompleteSuggestions = result;
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao buscar registros com o termo: ' + query);
	    });
	}
	
	
	contaBancariaAgenciaAutoCompleteFieldConverter(agencia: AgenciaBancariaAutoComplete) {
		if (agencia) {
			return (agencia.numeroAgencia || '<nulo>') + ' - ' + (agencia.digitoAgencia || '<nulo>') + ' - ' + (agencia.endereco || '<nulo>');
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

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

import { CartaoCreditoService } from './cartaocredito.service';
import { FinanceiroContasReceberTranslationService } from './../i18n/financeiro-contasreceber-translation.service';
import { CartaoCredito } from './cartaocredito.model';
import { CartaoCreditoListFilter } from './cartaocredito.model';
import { SortField } from './cartaocredito.model';

import { BancoAutoComplete } from './../banco/banco.model';

import { BandeiraCartaoAutoComplete } from './../bandeiracartao/bandeiracartao.model';

@Component({
  selector: 'app-list-cartaocredito.component',
  templateUrl: './list-cartaocredito.component.html',
  styleUrls: ['./list-cartaocredito.component.css']
})

export class CartaoCreditoListComponent implements OnInit {
	
	cartaoCreditoListItems: CartaoCredito[];
	cartaoCreditoListTotalElements = 0;
	cartaoCreditoListFilter = new CartaoCreditoListFilter();
	
	
	
	constructor(
	    private cartaoCreditoService: CartaoCreditoService,
	    private financeiroContasReceberTranslationService: FinanceiroContasReceberTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
	}
	
	cartaoCreditoList(pageNumber = 0) {
	    this.cartaoCreditoListFilter.pageNumber = pageNumber;
	    this.cartaoCreditoService
	    .cartaoCreditoList(this.cartaoCreditoListFilter)
	    .then(result => {
	      	this.cartaoCreditoListItems = result.items;
	      	this.cartaoCreditoListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	cartaoCreditoFilterSearch() {
	    this.cartaoCreditoList(0);
	}
	
	deleteCartaoCredito(cartaoCredito: CartaoCredito) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.cartaoCreditoService.delete(cartaoCredito.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.cartaoCreditoList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	cartaoCreditoListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.cartaoCreditoListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.cartaoCreditoListFilter.sortField = new SortField('id', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.cartaoCreditoList(pageNumber);
	}
	
	
	cartaoCreditoBancoAutoCompleteFieldConverter(banco: BancoAutoComplete) {
		if (banco) {
			return (banco.numero || '<nulo>') + ' - ' + (banco.nome || '<nulo>');
		} else {
			return null;
		}
	}
	
	cartaoCreditoBandeiraCartaoAutoCompleteFieldConverter(bandeiraCartao: BandeiraCartaoAutoComplete) {
		if (bandeiraCartao) {
			return (bandeiraCartao.nomeBandeira || '<nulo>');
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

/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.789
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {MessageService} from 'primeng/api';

import { ContaReceber } from './contareceber.model';
import { ContaReceberService } from './contareceber.service';
import { FinanceiroContasReceberTranslationService } from './../i18n/financeiro-contasreceber-translation.service';
import * as moment from 'moment';

import { PlanoContaService } from './../planoconta/planoconta.service';
import { PlanoConta } from './../planoconta/planoconta.model';
import { PlanoContaAutoComplete } from './../planoconta/planoconta.model';

import { ContaBancariaService } from './../contabancaria/contabancaria.service';
import { ContaBancaria } from './../contabancaria/contabancaria.model';
import { ContaBancariaAutoComplete } from './../contabancaria/contabancaria.model';

import { CartaoCreditoService } from './../cartaocredito/cartaocredito.service';
import { CartaoCredito } from './../cartaocredito/cartaocredito.model';
import { CartaoCreditoAutoComplete } from './../cartaocredito/cartaocredito.model';

import { ClienteService } from './../cliente/cliente.service';
import { Cliente } from './../cliente/cliente.model';
import { ClienteAutoComplete } from './../cliente/cliente.model';

import { FormaPagamento } from './../enums/financeiro-contasreceber-enums.model';
import {SelectItem} from 'primeng/api';


@Component({
  selector: 'app-crud-contareceber.component',
  templateUrl: './crud-contareceber.component.html',
  styleUrls: ['./crud-contareceber.component.css']
})

export class ContaReceberComponent implements OnInit {
	 
	numberOfCopies = 1;
	copiesReferenceFieldInterval = 30;
	
	copiesReferenceFieldOptions: SelectItem[];
	copiesReferenceField: SelectItem = { label: 'Vencimento', value: 'dataVencimento' };
	copiesReferenceFieldSelected: SelectItem;
	 
	contaReceber = new ContaReceber();
	contaReceberPlanoContasAutoCompleteSuggestions: PlanoContaAutoComplete[];
	
	
	contaReceberContaBancariaAutoCompleteSuggestions: ContaBancariaAutoComplete[];
	
	
	contaReceberCartaoCreditoAutoCompleteSuggestions: CartaoCreditoAutoComplete[];
	
	
	contaReceberClienteAutoCompleteSuggestions: ClienteAutoComplete[];
	contaReceberFormaPagamentoOptions: FormaPagamento[];
	
	constructor(
	    private contaReceberService: ContaReceberService,
	    private financeiroContasReceberTranslationService: FinanceiroContasReceberTranslationService,
	    private planoContaService: PlanoContaService,
	    
	    
	    private contaBancariaService: ContaBancariaService,
	    
	    
	    private cartaoCreditoService: CartaoCreditoService,
	    
	    
	    private clienteService: ClienteService,
	    private route: ActivatedRoute,
	    private messageService: MessageService
	) { 
		this.initializeContaReceberFormaPagamentoOptions();
		this.initializeCopiesReferenceFieldOptions();
	}
	
	ngOnInit() {
		this.initializeEnumFieldsWithDefault();
	    const id = this.route.snapshot.params['id'];
	    if (id) {
	      this.getContaReceberById(id);
	    }
	}
	
	begin(form: FormControl) {
	    form.reset();
	    setTimeout(function() {
	      this.contaReceber = new ContaReceber();
	      this.initializeEnumFieldsWithDefault();
	    }.bind(this), 1);
	}
	
	validateAllFormFields(form: FormGroup) {
	    Object.keys(form.controls).forEach(field => {
	      const control = form.get(field);
	
	      if (control instanceof FormControl) {
	        control.markAsDirty({ onlySelf: true });
	      } else if (control instanceof FormGroup) {
	        this.validateAllFormFields(control);
	      }
	    });
	}
	
	save(form: FormGroup) {
		if (!form.valid) {
	      this.validateAllFormFields(form);
	      return;
	    }
		    
	    if (this.isEditing) {
	      this.update();
	    } else {
	      this.create();
	    }
		this.initializeCopiesReferenceFieldOptions();
	}
	
	create() {
		
	    this.contaReceberService.create(this.contaReceber)
	    .then((contaReceber) => {
	      this.contaReceber = contaReceber;
	      this.showSuccess('Registro criado com sucesso!');
	    }).
	    catch(error => {
	      this.showError('Erro ao criar registro: ' + error);
	    });
	}
	
	update() {
	    this.contaReceberService.update(this.contaReceber)
	    .then((contaReceber) => {
	      this.contaReceber = contaReceber;
	      this.showSuccess('Registro alterado!');
	    })
	    .catch(error => {
	      this.showError('Erro ao atualizar registro: ' + error);
	    });
	}
	
	getContaReceberById(id: string) {
	    this.contaReceberService.retrieve(id)
	    .then((contaReceber) => this.contaReceber = contaReceber)
	    .catch(error => {
	      this.showError('Erro ao buscar registro: ' + id);
	    });
	}
	
	get isEditing() {
	    return Boolean(this.contaReceber.id);
	}
	
	initializeEnumFieldsWithDefault() {
		this.contaReceber.formaPagamento = this.contaReceberFormaPagamentoOptions[0].value;
	}
	
	
	contaReceberPlanoContasAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.contaReceber.planoContas = null;
	}
	
	contaReceberPlanoContasAutoComplete(event) {
	    const query = event.query;
	    this.contaReceberService
	      .planoContaPlanoContasAutoComplete(query)
	      .then((result) => {
	        this.contaReceberPlanoContasAutoCompleteSuggestions = result as PlanoContaAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	contaReceberPlanoContasAutoCompleteFieldConverter(planoContas: PlanoContaAutoComplete) {
		if (planoContas) {
			return (planoContas.codigo || '<nulo>') + ' - ' + (planoContas.descricao || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	contaReceberContaBancariaAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.contaReceber.contaBancaria = null;
	}
	
	contaReceberContaBancariaAutoComplete(event) {
	    const query = event.query;
	    this.contaReceberService
	      .contaBancariaContaBancariaAutoComplete(query)
	      .then((result) => {
	        this.contaReceberContaBancariaAutoCompleteSuggestions = result as ContaBancariaAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	contaReceberContaBancariaAutoCompleteFieldConverter(contaBancaria: ContaBancariaAutoComplete) {
		if (contaBancaria) {
			return (contaBancaria.nomeTitular || '<nulo>') + ' - ' + (contaBancaria.numeroConta || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	contaReceberCartaoCreditoAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.contaReceber.cartaoCredito = null;
	}
	
	contaReceberCartaoCreditoAutoComplete(event) {
	    const query = event.query;
	    this.contaReceberService
	      .cartaoCreditoCartaoCreditoAutoComplete(query)
	      .then((result) => {
	        this.contaReceberCartaoCreditoAutoCompleteSuggestions = result as CartaoCreditoAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	contaReceberCartaoCreditoAutoCompleteFieldConverter(cartaoCredito: CartaoCreditoAutoComplete) {
		if (cartaoCredito) {
			return (cartaoCredito.nomeTitular || '<nulo>') + ' - ' + (cartaoCredito.numeroCartao || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	contaReceberClienteAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.contaReceber.cliente = null;
	}
	
	contaReceberClienteAutoComplete(event) {
	    const query = event.query;
	    this.contaReceberService
	      .clienteClienteAutoComplete(query)
	      .then((result) => {
	        this.contaReceberClienteAutoCompleteSuggestions = result as ClienteAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	contaReceberClienteAutoCompleteFieldConverter(cliente: ClienteAutoComplete) {
		if (cliente) {
			return (cliente.nome || '<nulo>');
		} else {
			return null;
		}
	}
	
	private initializeContaReceberFormaPagamentoOptions() {
	    this.contaReceberFormaPagamentoOptions = [
	    	{ label: this.getTranslation('financeiro.contas_receber.contaReceber_formaPagamento_dinheiro'), value: 'DINHEIRO' }, 
	    	{ label: this.getTranslation('financeiro.contas_receber.contaReceber_formaPagamento_conta_bancaria'), value: 'CONTA_BANCARIA' }, 
	    	{ label: this.getTranslation('financeiro.contas_receber.contaReceber_formaPagamento_cartao_credito'), value: 'CARTAO_CREDITO' }, 
	    	{ label: this.getTranslation('financeiro.contas_receber.contaReceber_formaPagamento_cheque'), value: 'CHEQUE' }, 
	    	{ label: this.getTranslation('financeiro.contas_receber.contaReceber_formaPagamento_outros'), value: 'OUTROS' }
	    ];
	}
	  
	
	public showSuccess(msg: string) {
	    this.messageService.add({severity: 'success', summary: 'Successo', detail: msg});
	}
	
	public showError(msg: string) {
	    this.messageService.add({severity: 'error', summary: 'Erro', detail: msg});
	}
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroContasReceberTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
	
	
	actionFazerCopiasContaReceber(form: FormControl) {
	      if (!this.contaReceber.agrupador) {
	        // this.copiesMustHaveGroup = true;
	        this.showError('Campo \'Agrupador\' deve ser informado para gerar cópias.');
	        return;
	      }
	      // this.copiesMustHaveGroup = false;
	
	      this.contaReceberService.actionFazerCopiasContaReceber(this.contaReceber.id, this.numberOfCopies,
	        this.copiesReferenceFieldInterval, this.contaReceber.agrupador)
		    .then(() => {
	        // this.copiesMustHaveGroup = false;
	        this.showSuccess('Operação realizada com sucesso!');
		    }).
		    catch(error => {
	        // this.copiesMustHaveGroup = false;
	        const message =  JSON.parse(error._body).message || 'Não foi possível realizar a operação';
	        console.log(error);
		      this.showError('Erro: ' + message);
		    });
	}
	 
	initializeCopiesReferenceFieldOptions() {
	    this.copiesReferenceFieldOptions = [
	      this.copiesReferenceField
	    ];
	
	    this.copiesReferenceFieldSelected = this.copiesReferenceField;
	    
	    this.numberOfCopies = 1;
	    this.copiesReferenceFieldInterval = 30;
	}
	
	
}

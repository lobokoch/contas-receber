/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.789
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import * as moment from 'moment';

import { HttpClientWithToken } from '../../../../security/http-client-token';

import { ContaReceber } from './contareceber.model';
import { ContaReceberAutoComplete } from './contareceber.model';
import { PlanoConta } from './../planoconta/planoconta.model';
import { ContaBancaria } from './../contabancaria/contabancaria.model';
import { CartaoCredito } from './../cartaocredito/cartaocredito.model';
import { Cliente } from './../cliente/cliente.model';
import { ContaReceberListFilter } from './contareceber.model';
import { ContaReceberDescricaoAutoComplete } from './contareceber.model';
import { ContaReceberAgrupadorAutoComplete } from './contareceber.model';
import { ContaReceberSumFields } from './contareceber.model';
import { environment } from 'src/environments/environment';
import { PlanoContaAutoComplete } from './../planoconta/planoconta.model';
import { ContaBancariaAutoComplete } from './../contabancaria/contabancaria.model';
import { CartaoCreditoAutoComplete } from './../cartaocredito/cartaocredito.model';
import { ClienteAutoComplete } from './../cliente/cliente.model';

@Injectable()
export class ContaReceberService {
	
	url = environment.apiUrl + '/financeiro/contas_receber/entities/contaReceber';
	
	constructor(private http: HttpClientWithToken) { }
	
	// TODO: Provisório
	private getHeaders(): Headers {
		const headers = new Headers();
	    
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
	
	create(contaReceber: ContaReceber): Promise<ContaReceber> {
		const headers = this.getHeaders();    
	
	    return this.http.post(this.url, contaReceber, { headers })
	    .toPromise()
	    .then(response => {
	      const created = response as ContaReceber;
	      this.adjustNullEntitySlots([created]);
	      this.adjustEntityDates([created]);
	      return created;
	    });
	}
	
	update(contaReceber: ContaReceber): Promise<ContaReceber> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/${contaReceber.id}`, contaReceber, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as ContaReceber;
	      this.adjustNullEntitySlots([updated]);
	      this.adjustEntityDates([updated]);
	      return updated;
	    });
	}
	
	delete(id: string): Promise<void> {
	    return this.http.delete(`${this.url}/${id}`)
	    .toPromise()
	    .then(() => null);
	}
	
	retrieve(id: string): Promise<ContaReceber> {
	    const headers = this.getHeaders();
	    return this.http.get<ContaReceber>(`${this.url}/${id}`, { headers })
	    .toPromise()
	    .then(response => {
	      const contaReceber = response as ContaReceber;
	      this.adjustNullEntitySlots([contaReceber]);
	      this.adjustEntityDates([contaReceber]);
	      return contaReceber;
	    });
	}
	
	 
	actionBaixarContaComUmClique(id: string): Promise<void> {
		const headers = this.getHeaders();
		
		return this.http.put(`${this.url}/actionBaixarContaComUmClique/${id}`, { headers })
		.toPromise()
		.then(() => null);
	}
	 
	actionEstornarRecebimentoContaComUmClique(id: string): Promise<void> {
		const headers = this.getHeaders();
		
		return this.http.put(`${this.url}/actionEstornarRecebimentoContaComUmClique/${id}`, { headers })
		.toPromise()
		.then(() => null);
	}
	 
	actionFazerCopiasContaReceber(id: string, numberOfCopies: Number, referenceFieldInterval: Number, agrupador: String): Promise<void> {
	    const headers = this.getHeaders();
	      const entityCopy = { id, numberOfCopies, referenceFieldInterval, agrupador };
		    return this.http.post(`${this.url}/actionFazerCopiasContaReceber`, entityCopy, { headers })
		    .toPromise()
		    .then( () => null);
	}
	
	private adjustEntityDates(entityList: ContaReceber[]) {
		entityList.forEach(contaReceber => {
		      if (contaReceber.dataVencimento) {
		        contaReceber.dataVencimento = moment(contaReceber.dataVencimento, 'YYYY-MM-DD').toDate();
		      }
		      	
		      
		      if (contaReceber.dataPagamento) {
		        contaReceber.dataPagamento = moment(contaReceber.dataPagamento, 'YYYY-MM-DD').toDate();
		      }
		      	
		});
	}
	
	private adjustNullEntitySlots(entityList: ContaReceber[]) {
		/*entityList.forEach(contaReceber => {
		      if (!contaReceber.planoContas) {
		        contaReceber.planoContas = new PlanoConta();
		      }
		      	
		      
		      if (!contaReceber.contaBancaria) {
		        contaReceber.contaBancaria = new ContaBancaria();
		      }
		      	
		      
		      if (!contaReceber.cartaoCredito) {
		        contaReceber.cartaoCredito = new CartaoCredito();
		      }
		      	
		      
		      if (!contaReceber.cliente) {
		        contaReceber.cliente = new Cliente();
		      }
		      	
		});*/
	}
	
	autoComplete(query: string): Promise<ContaReceberAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<ContaReceberAutoComplete[]>(`${this.url}/autoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ContaReceberAutoComplete[];
	        return result;
	      });
	
	}
	
							
	// Begin relationships autoComplete 
	
	planoContaPlanoContasAutoComplete(query: string): Promise<PlanoContaAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<PlanoContaAutoComplete[]>(`${this.url}/planoContaPlanoContasAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as PlanoContaAutoComplete[];
	        return result;
	      });
	
	}
	
	
	contaBancariaContaBancariaAutoComplete(query: string): Promise<ContaBancariaAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<ContaBancariaAutoComplete[]>(`${this.url}/contaBancariaContaBancariaAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ContaBancariaAutoComplete[];
	        return result;
	      });
	
	}
	
	
	cartaoCreditoCartaoCreditoAutoComplete(query: string): Promise<CartaoCreditoAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<CartaoCreditoAutoComplete[]>(`${this.url}/cartaoCreditoCartaoCreditoAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as CartaoCreditoAutoComplete[];
	        return result;
	      });
	
	}
	
	
	clienteClienteAutoComplete(query: string): Promise<ClienteAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<ClienteAutoComplete[]>(`${this.url}/clienteClienteAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ClienteAutoComplete[];
	        return result;
	      });
	
	}
	
	// End relationships autoComplete
	
				
	
	contaReceberDescricaoAutoComplete(query: string): Promise<any> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<any>(`${this.url}/contaReceberDescricaoAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ContaReceberDescricaoAutoComplete[];
	        return result;
	      });
	
	}
	
	contaReceberAgrupadorAutoComplete(query: string): Promise<any> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<any>(`${this.url}/contaReceberAgrupadorAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ContaReceberAgrupadorAutoComplete[];
	        return result;
	      });
	
	}
	
	contaReceberList(contaReceberListFilter: ContaReceberListFilter): Promise<any> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(contaReceberListFilter);
	
	    return this.http.get<any>(this.url, { headers, params })
	      .toPromise()
	      .then(response => {
	        const data = response;
	        const items = data.content; /* array of ContaReceber */
	        const totalElements = data.totalElements;
	
	        this.adjustNullEntitySlots(items);
	        this.adjustEntityDates(items);
	
	        const result = {
	          items,
	          totalElements
	        };
	
	        return result;
	      });
	}
	
	
	getContaReceberSumFields(contaReceberListFilter: ContaReceberListFilter): Promise<ContaReceberSumFields> {
	    const headers = this.getHeaders();
	    
		const params = this.mountAndGetSearchParams(contaReceberListFilter);
		return this.http.get<any>(`${this.url}/contaReceberSumFields`, { headers, params })
		  .toPromise()
		  .then(response => {
		    const result = response;
		    return result;
		  });
	}
	
	mountAndGetSearchParams(filter: ContaReceberListFilter): HttpParams {
	    let params = new HttpParams();
	    if (filter.pageNumber) {
	      params = params.set('page', filter.pageNumber.toString());
	    }
	
	    if (filter.pageSize) {
	      params = params.set('size', filter.pageSize.toString());
	    }
		
		// descricao
		if (filter.descricao) {
			const descricao = filter.descricao.map(item => item.descricao).join(',');
			params = params.set('descricao', descricao);
		}
		
		// dataVencimentoFrom
		if (filter.dataVencimentoFrom) {
		const value = this.dateToStr(filter.dataVencimentoFrom);
			params = params.set('dataVencimentoFrom', value);
		}
		
		// dataVencimentoTo
		if (filter.dataVencimentoTo) {
		const value = this.dateToStr(filter.dataVencimentoTo);
			params = params.set('dataVencimentoTo', value);
		}
		
		// dataPagamentoIsNotNull
		if (filter.dataPagamentoIsNotNull) {
			const value = filter.dataPagamentoIsNotNull ? 'true' : 'false';
			params = params.set('dataPagamentoIsNotNull', value);
		}
		
		// dataPagamentoIsNull
		if (filter.dataPagamentoIsNull) {
			const value = filter.dataPagamentoIsNull ? 'true' : 'false';
			params = params.set('dataPagamentoIsNull', value);
		}
		
		// agrupador
		if (filter.agrupador) {
			const agrupador = filter.agrupador.map(item => item.agrupador).join(',');
			params = params.set('agrupador', agrupador);
		}
	
	    // Sort
	    if (filter.sortField) {
	      // search/nameStartsWith?name=K&sort=name,desc
	      const sortField = filter.sortField;
	      const sortValue = `${sortField.field},${sortField.order > 0 ? 'asc' : 'desc'}`;
	      params = params.set('sort', sortValue);
	    }
	
	    return params;
	  }
	
	dateToStr(data: Date): string {
	    return moment(data).format('YYYY-MM-DD');
	}
	
	/*** TODO: avaliar se vai ser feito isso.
	replicateContaReceber(id: string, groupId: string, quantity: number): Promise<boolean> {
	    const headers = this.getHeaders();
	
	    const payload = new ReplicateContaReceberPayload(id, quantity, groupId);
	    return this.http.post(`${this.url}/replicateContaReceber`, payload, { headers } )
	    .toPromise()
	    .then(response => {
	      return response === true;
	    });
	}
		
	getTotaisfilterContaReceber(filter: ContaReceberrListFilter): Promise<TotaisfilterContaReceber> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(filter);
	    return this.http.get<TotaisfilterContaReceber>(`${this.url}/getTotaisfilterContaReceber`, { headers, params })
	    .toPromise()
	    .then(response => {
	      const result = response as TotaisfilterContaReceber;
	      return result;
	    });
	}
	*/
}


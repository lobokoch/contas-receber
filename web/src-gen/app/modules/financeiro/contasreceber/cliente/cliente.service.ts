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

import { Cliente } from './cliente.model';
import { ClienteAutoComplete } from './cliente.model';
import { ClienteListFilter } from './cliente.model';
import { ClienteNomeAutoComplete } from './cliente.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class ClienteService {
	
	url = environment.apiUrl + '/financeiro/contas_receber/entities/cliente';
	
	constructor(private http: HttpClientWithToken) { }
	
	// TODO: Provisório
	private getHeaders(): Headers {
		const headers = new Headers();
	    
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
	
	create(cliente: Cliente): Promise<Cliente> {
		const headers = this.getHeaders();    
	
	    return this.http.post(this.url, cliente, { headers })
	    .toPromise()
	    .then(response => {
	      const created = response as Cliente;
	      return created;
	    });
	}
	
	update(cliente: Cliente): Promise<Cliente> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/${cliente.id}`, cliente, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as Cliente;
	      return updated;
	    });
	}
	
	delete(id: string): Promise<void> {
	    return this.http.delete(`${this.url}/${id}`)
	    .toPromise()
	    .then(() => null);
	}
	
	retrieve(id: string): Promise<Cliente> {
	    const headers = this.getHeaders();
	    return this.http.get<Cliente>(`${this.url}/${id}`, { headers })
	    .toPromise()
	    .then(response => {
	      const cliente = response as Cliente;
	      return cliente;
	    });
	}
	
	
	
	
	autoComplete(query: string): Promise<ClienteAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<ClienteAutoComplete[]>(`${this.url}/autoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ClienteAutoComplete[];
	        return result;
	      });
	
	}
	
				
	
	clienteNomeAutoComplete(query: string): Promise<any> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<any>(`${this.url}/clienteNomeAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as ClienteNomeAutoComplete[];
	        return result;
	      });
	
	}
	
	clienteList(clienteListFilter: ClienteListFilter): Promise<any> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(clienteListFilter);
	
	    return this.http.get<any>(this.url, { headers, params })
	      .toPromise()
	      .then(response => {
	        const data = response;
	        const items = data.content; /* array of Cliente */
	        const totalElements = data.totalElements;
	
	
	        const result = {
	          items,
	          totalElements
	        };
	
	        return result;
	      });
	}
	
	
	mountAndGetSearchParams(filter: ClienteListFilter): HttpParams {
	    let params = new HttpParams();
	    if (filter.pageNumber) {
	      params = params.set('page', filter.pageNumber.toString());
	    }
	
	    if (filter.pageSize) {
	      params = params.set('size', filter.pageSize.toString());
	    }
		
		// nome
		if (filter.nome) {
			const nome = filter.nome.map(item => item.nome).join(',');
			params = params.set('nome', nome);
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
	replicateCliente(id: string, groupId: string, quantity: number): Promise<boolean> {
	    const headers = this.getHeaders();
	
	    const payload = new ReplicateClientePayload(id, quantity, groupId);
	    return this.http.post(`${this.url}/replicateCliente`, payload, { headers } )
	    .toPromise()
	    .then(response => {
	      return response === true;
	    });
	}
		
	getTotaisfilterCliente(filter: ClienterListFilter): Promise<TotaisfilterCliente> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(filter);
	    return this.http.get<TotaisfilterCliente>(`${this.url}/getTotaisfilterCliente`, { headers, params })
	    .toPromise()
	    .then(response => {
	      const result = response as TotaisfilterCliente;
	      return result;
	    });
	}
	*/
}


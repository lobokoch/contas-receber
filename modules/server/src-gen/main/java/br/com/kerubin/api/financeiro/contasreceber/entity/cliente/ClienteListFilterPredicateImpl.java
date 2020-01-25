/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class ClienteListFilterPredicateImpl implements ClienteListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(ClienteListFilter clienteListFilter) {
		if (clienteListFilter == null) {
			return null;
		}
		
		QClienteEntity qEntity = QClienteEntity.clienteEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: nome
		if (!CollectionUtils.isEmpty(clienteListFilter.getNome())) {
			BooleanExpression inExpression = qEntity.nome.in(clienteListFilter.getNome());
			where.and(inExpression);
		}
		// End field: nome
		
		return where;
	}

}


/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class ContaReceberListFilterPredicateImpl implements ContaReceberListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(ContaReceberListFilter contaReceberListFilter) {
		if (contaReceberListFilter == null) {
			return null;
		}
		
		QContaReceberEntity qEntity = QContaReceberEntity.contaReceberEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		if (!CollectionUtils.isEmpty(contaReceberListFilter.getDescricao())) {
			BooleanExpression inExpression = qEntity.descricao.in(contaReceberListFilter.getDescricao());
			where.and(inExpression);
		}
		
		java.time.LocalDate fieldFrom = contaReceberListFilter.getDataVencimentoFrom();
		java.time.LocalDate fieldTo = contaReceberListFilter.getDataVencimentoTo();
		
		if (fieldFrom != null && fieldTo != null) {
			if (fieldFrom.isAfter(fieldTo)) {
				throw new IllegalArgumentException("Valor de \"Vencimento de\" não pode ser maior do que valor de \"Até\".");
			}
			
			BooleanExpression between = qEntity.dataVencimento.between(fieldFrom, fieldTo);
			where.and(between);
		}
		else {
			if (fieldFrom != null) {
				where.and(qEntity.dataVencimento.goe(fieldFrom));
			}
			else if (fieldTo != null) {
				where.and(qEntity.dataVencimento.loe(fieldTo));				
			}
		}
		
		if ( ! (contaReceberListFilter.isDataPagamentoIsNull() && contaReceberListFilter.isDataPagamentoIsNotNull()) ) {
					
			if (contaReceberListFilter.isDataPagamentoIsNull()) {
				where.and(qEntity.dataPagamento.isNull());
			}
			else {
				where.and(qEntity.dataPagamento.isNotNull());				
			}
			
			if (contaReceberListFilter.isDataPagamentoIsNotNull()) {
				where.and(qEntity.dataPagamento.isNotNull());
			}
			else {
				where.and(qEntity.dataPagamento.isNull());				
			}
			
		}
		
		if (!CollectionUtils.isEmpty(contaReceberListFilter.getAgrupador())) {
			BooleanExpression inExpression = qEntity.agrupador.in(contaReceberListFilter.getAgrupador());
			where.and(inExpression);
		}
		
		return where;
	}

}


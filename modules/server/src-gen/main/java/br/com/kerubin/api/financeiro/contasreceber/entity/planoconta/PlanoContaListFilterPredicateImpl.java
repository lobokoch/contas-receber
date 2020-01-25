/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.planoconta;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class PlanoContaListFilterPredicateImpl implements PlanoContaListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(PlanoContaListFilter planoContaListFilter) {
		if (planoContaListFilter == null) {
			return null;
		}
		
		QPlanoContaEntity qEntity = QPlanoContaEntity.planoContaEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: codigo
		if (!CollectionUtils.isEmpty(planoContaListFilter.getCodigo())) {
			BooleanExpression inExpression = qEntity.codigo.in(planoContaListFilter.getCodigo());
			where.and(inExpression);
		}
		// End field: codigo
		
		// Begin field: descricao
		if (!CollectionUtils.isEmpty(planoContaListFilter.getDescricao())) {
			BooleanExpression inExpression = qEntity.descricao.in(planoContaListFilter.getDescricao());
			where.and(inExpression);
		}
		// End field: descricao
		
		// Begin field: Ativo
		if ( ! (planoContaListFilter.isAtivoIsNull() && planoContaListFilter.isAtivoIsNotNull()) ) {
			
			if (planoContaListFilter.getAtivoIsNull() != null) {
				if (planoContaListFilter.isAtivoIsNull()) {
					where.and(qEntity.ativo.isNull().or(qEntity.ativo.isFalse()));
				}
				else {
					where.and(qEntity.ativo.isNotNull().and(qEntity.ativo.isTrue()));
				}
			}
			
			if (planoContaListFilter.getAtivoIsNotNull() != null) {
				if (planoContaListFilter.isAtivoIsNotNull()) {
					where.and(qEntity.ativo.isNotNull().and(qEntity.ativo.isTrue()));
				}
				else {
					where.and(qEntity.ativo.isNull().or(qEntity.ativo.isFalse()));
				}
			}
			
		}
		// End field: Ativo
		
		return where;
	}

}


/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface ContaReceberRepository extends JpaRepository<ContaReceberEntity, java.util.UUID>, QuerydslPredicateExecutor<ContaReceberEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.descricao as descricao from ContaReceberEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaReceberAutoComplete> autoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.descricao as descricao from ContaReceberEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaReceberDescricaoAutoComplete> contaReceberDescricaoAutoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.agrupador as agrupador from ContaReceberEntity ac where ( upper(ac.agrupador) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaReceberAgrupadorAutoComplete> contaReceberAgrupadorAutoComplete(@Param("query") String query);
	
}

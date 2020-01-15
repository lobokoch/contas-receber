/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:19.082
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface ClienteRepository extends JpaRepository<ClienteEntity, java.util.UUID>, QuerydslPredicateExecutor<ClienteEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.nome as nome from ClienteEntity ac where ( upper(ac.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ClienteAutoComplete> autoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.nome as nome from ClienteEntity ac where ( upper(ac.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ClienteNomeAutoComplete> clienteNomeAutoComplete(@Param("query") String query);
}
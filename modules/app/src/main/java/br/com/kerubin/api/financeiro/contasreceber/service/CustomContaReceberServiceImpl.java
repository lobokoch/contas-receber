package br.com.kerubin.api.financeiro.contasreceber.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.contasreceber.FinanceiroContasReceberConstants;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberServiceImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.QPlanoContaEntity;
import br.com.kerubin.api.financeiro.contasreceber.event.ContaReceberEvent;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Service
public class CustomContaReceberServiceImpl extends ContaReceberServiceImpl {
	
	private static final String ENTITY_KEY = "entity.ContaReceber";
	private static final UUID PLANO_CONTA_RECEITAS_ID = UUID.fromString("1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe");
	
	@Autowired
	private DomainEntityEventsPublisher publisher;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query) {
		JPAQueryFactory queryDSL = new JPAQueryFactory(em);
		QPlanoContaEntity qPlanoConta = QPlanoContaEntity.planoContaEntity;
		QPlanoContaEntity qPlanoContaPai = new QPlanoContaEntity("planoContaPai");
		
		BooleanBuilder where = new BooleanBuilder();
		if (isNotEmpty(query)) {
			where.and(qPlanoConta.descricao.containsIgnoreCase(query).or(qPlanoContaPai.descricao.containsIgnoreCase(query)));
		}
		
		where.and(qPlanoConta.ativo.isTrue())
		.and(qPlanoConta.deleted.isFalse().or(qPlanoConta.deleted.isNull()));
		
		StringExpression descricaoConcatenation = emptyIfNull(qPlanoContaPai.codigo)
				.concat(" - ")
				.concat(emptyIfNull(qPlanoContaPai.descricao))
				.concat(" / ")
				.concat(qPlanoConta.codigo)
				.concat(" - ")
				.concat(qPlanoConta.descricao);
		
		JPAQuery<Tuple> query_ = queryDSL.select(
				qPlanoConta.id, 
				qPlanoConta.codigo,
				descricaoConcatenation
				)
		.from(qPlanoConta)
		.leftJoin(qPlanoConta.planoContaPai, qPlanoContaPai).on(qPlanoContaPai.id.eq(qPlanoConta.planoContaPai.id))
		.where(where)
		.orderBy(qPlanoConta.codigo.asc());
		
		List<Tuple> tuples = query_.fetch();
		
		List<PlanoContaAutoComplete> items = new ArrayList<>();
		if (tuples != null && !tuples.isEmpty()) {
			for (Tuple tuple: tuples) {
				PlanoContaAutoCompleteImpl plano = new PlanoContaAutoCompleteImpl();
				plano.setId(tuple.get(0, UUID.class));
				plano.setCodigo(tuple.get(1, String.class));
				plano.setDescricao(tuple.get(2, String.class));
				items.add(plano);
			} // for
		}
		
		if (items.size() > 0) {
			PlanoContaAutoComplete plano = items.get(0);
			if (PLANO_CONTA_RECEITAS_ID.equals(plano.getId())) { // "descricao": "- / 1-DESPESAS"
				((PlanoContaAutoCompleteImpl) plano).setDescricao(plano.getDescricao().replace(" -  / ", ""));
			}
		}
		
		items = items.stream()
				.sorted(Comparator.comparingInt(this::codigoToInt))
				.collect(Collectors.toList());
		
		return items;
	}
	
	
	@Transactional
	@Override
	public ContaReceberEntity create(ContaReceberEntity contaReceberEntity) {
		ContaReceberEntity entity = super.create(contaReceberEntity);
		
		if (isNotEmpty(entity.getDataPagamento())) {
			publishEventContaPaga(entity);
		}
		
		return entity;
	}
	
	@Transactional
	@Override
	public ContaReceberEntity update(UUID id, ContaReceberEntity contaReceberEntity) {
		ContaReceberEntity beforeUpdateEntity = read(contaReceberEntity.getId());
		beforeUpdateEntity = beforeUpdateEntity.clone();
		
		ContaReceberEntity entity = super.update(id, contaReceberEntity);
		
		if (isNotEmpty(beforeUpdateEntity.getDataPagamento()) && isEmpty(entity.getDataPagamento())) { // Estornou
			if (isEmpty(entity.getValorPago())) {
				entity.setValorPago(beforeUpdateEntity.getValorPago());
			}
			publishEventContaEstornada(entity);
		}
		else if (isEmpty(beforeUpdateEntity.getDataPagamento()) && isNotEmpty(entity.getDataPagamento())) { // Pagou
			publishEventContaPaga(entity);
		}
		
		return entity;
	}
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(UUID id) {
		
		// Baixa a conta
		super.actionBaixarContaComDataPagamentoIgualDataVenciento(id);
		
		publicarContaPaga(id);
	}
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoHoje(UUID id) {
		
		// Baixa a conta
		super.actionBaixarContaComDataPagamentoHoje(id);
		
		publicarContaPaga(id);
	}
	
	private void publicarContaPaga(UUID id) {
		// Busca a conta atualziada
		ContaReceberEntity entity = getContaReceberEntity(id);
		
		// Publica a mensagem de conta paga
		publishEventContaPaga(entity);
	}
	
	@Transactional
	@Override
	public void actionEstornarRecebimentoContaComUmClique(UUID id) {
		// Pega o valor antes de estornar.
		ContaReceberEntity entity = getContaReceberEntity(id).clone();
		
		// Estorna
		super.actionEstornarRecebimentoContaComUmClique(id);
		
		// Pública estorno
		publishEventContaEstornada(entity);
	}
	
	private void publishEventContaPaga(ContaReceberEntity entity) {
		// Publica a mensagem de conta paga
		if (entity.getDataPagamento() != null) {
			publishEvent(entity, ContaReceberEvent.CONTA_RECEBER_CONTAPAGA);
		}
	}
	
	private void publishEventContaEstornada(ContaReceberEntity entity) {
		// Pública estorno
		publishEvent(entity, ContaReceberEvent.CONTA_RECEBER_CONTAESTORNADA);
	}
	
	protected void publishEvent(ContaReceberEntity entity, String eventName) {
		try {
			DomainEvent event = new ContaReceberEvent(entity.getId(), 
				entity.getPlanoContas() != null ? entity.getPlanoContas().getId() : null, 
				entity.getDescricao(), 
				entity.getFormaPagamento(), 
				entity.getContaBancaria() != null ? entity.getContaBancaria().getId() : null, 
				entity.getCartaoCredito() != null ? entity.getCartaoCredito().getId() : null, 
				entity.getDataPagamento(), 
				entity.getValorPago(),
				entity.getCliente() != null ? entity.getCliente().getId() : null, 
				entity.getNumDocumento());
			
			DomainEventEnvelope<DomainEvent> envelope = DomainEventEnvelopeBuilder
					.getBuilder(eventName, event)
					.domain(FinanceiroContasReceberConstants.DOMAIN)
					.service(FinanceiroContasReceberConstants.SERVICE)
					.key(ENTITY_KEY)
					.tenant(ServiceContext.getTenant())
					.user(ServiceContext.getUser())
					.build();
			
			publisher.publish(envelope);
		}
		catch (Exception e) {
			log.error("Error publishing event: " + eventName + ", entity: " + entity);
		}
	}
	
	private int codigoToInt(Object toOrderObj) {
		if (toOrderObj != null && toOrderObj instanceof PlanoContaAutoComplete) {
			PlanoContaAutoComplete toOrder = (PlanoContaAutoComplete) toOrderObj;
			String codigo = toOrder.getCodigo();
			if (isNotEmpty(codigo)) {
				codigo = codigo.replace(".", "");
				try {
					return Integer.parseInt(codigo);
				} catch(Exception e) {
					return 0;
				}
			}
		}
		
		return 0;
	}
	
	private static StringExpression emptyIfNull(StringExpression expression) {
	    return expression.coalesce("").asString();
	}

}

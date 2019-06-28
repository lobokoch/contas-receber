package br.com.kerubin.api.financeiro.contasreceber.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.contasreceber.FinanceiroContasReceberConstants;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberServiceImpl;
import br.com.kerubin.api.financeiro.contasreceber.event.ContaReceberEvent;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;

@Primary
@Service
public class CustomContaReceberServiceImpl extends ContaReceberServiceImpl {
	
	private static final String ENTITY_KEY = "entity.ContaReceber";
	
	@Autowired
	private DomainEntityEventsPublisher publisher;
	
	@Transactional
	@Override
	public void actionBaixarContaComUmClique(UUID id) {
		
		// Baixa a conta
		super.actionBaixarContaComUmClique(id);
		
		// Busca a conta atualziada
		ContaReceberEntity entity = getContaReceberEntity(id);
		
		// Publica a mensagem de conta paga
		if (entity.getDataPagamento() != null) {
			publishEvent(entity, ContaReceberEvent.CONTA_RECEBER_CONTAPAGA);
		}
		
	}
	
	@Transactional
	@Override
	public void actionEstornarRecebimentoContaComUmClique(UUID id) {
		// Pega o valor antes de estornar.
		ContaReceberEntity entity = getContaReceberEntity(id).clone();
		
		// Estorna
		super.actionEstornarRecebimentoContaComUmClique(id);
		
		// Pública estorno
		publishEvent(entity, ContaReceberEvent.CONTA_RECEBER_CONTAESTORNADA);
	}
	
	protected void publishEvent(ContaReceberEntity entity, String eventName) {
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

}

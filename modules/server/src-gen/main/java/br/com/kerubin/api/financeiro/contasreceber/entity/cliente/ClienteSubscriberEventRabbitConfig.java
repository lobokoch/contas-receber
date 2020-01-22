/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:33.911
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import java.text.MessageFormat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;

import br.com.kerubin.api.cadastros.cliente.CadastrosClienteConstants;
import br.com.kerubin.api.financeiro.contasreceber.FinanceiroContasReceberConstants;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class ClienteSubscriberEventRabbitConfig {
	
	private static final String ENTITY_NAME = "Cliente";
	private static final String ENTITY_KEY = "entity";
	
	@Bean
	public TopicExchange clienteTopic() {
		String topicName = MessageFormat.format("{0}_{1}_{2}_{3}", //
			DomainEvent.APPLICATION, //
			CadastrosClienteConstants.DOMAIN, //
			CadastrosClienteConstants.SERVICE, //
			DomainEntityEventsPublisher.TOPIC_PREFFIX);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue clienteQueue() {
		// This service queue name for subscribe to the entity owner exchange topic.
		String queueName = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", //
			DomainEvent.APPLICATION, //
			FinanceiroContasReceberConstants.DOMAIN, //
			FinanceiroContasReceberConstants.SERVICE, //
			ENTITY_KEY, //
			ENTITY_NAME);
		
		return new Queue(queueName, true);
	}
	
	@Bean
	public Binding clienteBinding(@Qualifier("clienteTopic") TopicExchange topic, 
			@Qualifier("clienteQueue") Queue queue) {
		
		String rountingKey = MessageFormat.format("{0}.{1}.{2}.{3}.{4}", //
				DomainEvent.APPLICATION, //
				CadastrosClienteConstants.DOMAIN, //
				CadastrosClienteConstants.SERVICE, //
				ENTITY_KEY, //
				ENTITY_NAME);
		
		return BindingBuilder
				.bind(queue) //
				.to(topic) //
				.with(rountingKey);
	}

}

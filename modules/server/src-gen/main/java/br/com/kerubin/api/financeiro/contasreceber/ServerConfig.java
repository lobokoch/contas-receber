/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber;

import java.util.UUID;
// import org.modelmapper.ModelMapper;
// import org.modelmapper.convention.MatchingStrategies;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.kerubin.api.messaging.core.DomainEvent;

@Configuration
public class ServerConfig {
	
	// TODO: remover esse cara
	/*@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}*/
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer,
	        ConnectionFactory connectionFactory) {
		
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    configurer.configure(factory, connectionFactory);
	    factory.setConsumerTagStrategy(queue -> {
	    	StringBuilder sb = new StringBuilder(DomainEvent.APPLICATION).append("_")
	    			.append(FinanceiroContasReceberConstants.DOMAIN)
	    			.append("_")
	    			.append(FinanceiroContasReceberConstants.SERVICE)
	    			.append("_")
	    			.append(UUID.randomUUID().toString())
	    			//.append(".to.")
	    			//.append(queue)
	    			;
	    	String tag = sb.toString();
	    	return tag;
	    });
	    
	    factory.setAfterReceivePostProcessors(afterReceivePostProcessors());
	    
	    return factory;
	}
	
	@Bean
	public MessagePostProcessor afterReceivePostProcessors() {
		return new MessageAfterReceivePostProcessors();
	}
}

/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:33.911
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.cliente.entity.cliente.ClienteEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class ClienteSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteSubscriberEventHandler.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	ClienteDTOConverter clienteDTOConverter;
	
	@RabbitListener(queues = "#{clienteQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<ClienteEvent> envelope) {
		// WARNING: all the code MUST be inside the try catch code. If an error occurs, must be throw AmqpRejectAndDontRequeueException.
		try {
			switch (envelope.getPrimitive()) {
			case ClienteEvent.CLIENTE_CREATED:
			case ClienteEvent.CLIENTE_UPDATED:
			
				saveCliente(envelope.getPayload());
				break;
			
			case ClienteEvent.CLIENTE_DELETED:
				deleteCliente(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.cliente.entity.cliente.Cliente");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveCliente(ClienteEvent clienteEvent) {
		saveCliente(clienteEvent, false);
	}
	
	private void saveCliente(ClienteEvent clienteEvent, boolean isDeleted) {
		ClienteEntity entity = buildClienteEntity(clienteEvent);
		Optional<ClienteEntity> optionalEntity = clienteRepository.findById(clienteEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			clienteService.update(entity.getId(), entity);
		}
		else {
			clienteService.create(entity);
		}
	}
	
	private void deleteCliente(ClienteEvent clienteEvent) {
		Optional<ClienteEntity> optionalEntity = clienteRepository.findById(clienteEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				clienteService.delete(clienteEvent.getId());
			} catch(DataIntegrityViolationException e) {
				log.warn("Record cannot be deleted, will be deactivated instead: " + clienteEvent);
				try {
					saveCliente(clienteEvent, true);
				} catch(Exception e2) {
					log.error("Record cannot be deactivated: " + clienteEvent, e2);
				}
			}
		}
	}

	private ClienteEntity buildClienteEntity(ClienteEvent clienteEvent) {
		ClienteEntity entity = clienteDTOConverter.convertDtoToEntity(clienteEvent);
		return entity;
	}

}

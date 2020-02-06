package br.com.kerubin.api.financeiro.contasreceber.messaging.handler;

import java.text.MessageFormat;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.contasreceber.event.user.SecurityAuthorizationConstants;
import br.com.kerubin.api.financeiro.contasreceber.event.user.SecurityAuthorizationEventHandler;
import br.com.kerubin.api.financeiro.contasreceber.messaging.EventMessageNotHandledException;
import br.com.kerubin.api.financeiro.contasreceber.messaging.FinanceiroContasReceberEventHandler;
import br.com.kerubin.api.messaging.core.DomainMessage;


@Service
public class FinanceiroContasReceberEventHandlerImpl implements FinanceiroContasReceberEventHandler {
	
	@Inject
	private SecurityAuthorizationEventHandler securityAuthorizationEventHandler;
	
	@Override
	public void handleEvent(DomainMessage message) {
		String domain = message.getDomain();
		String service = message.getService();
		
		if (SecurityAuthorizationConstants.DOMAIN.equals(domain) && SecurityAuthorizationConstants.SERVICE.equals(service)) {
			securityAuthorizationEventHandler.doHandleEvent(message);
			return;
		}
		
		
		throw new EventMessageNotHandledException(MessageFormat.format("Event message not handled yet: {0}", message));
	}

}

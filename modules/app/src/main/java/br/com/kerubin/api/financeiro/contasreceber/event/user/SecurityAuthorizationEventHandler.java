package br.com.kerubin.api.financeiro.contasreceber.event.user;

import br.com.kerubin.api.messaging.core.DomainMessage;

public interface SecurityAuthorizationEventHandler {

	void doHandleEvent(DomainMessage message);

}

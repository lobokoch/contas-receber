-- Alteracao altamente destrutiva
alter table conta_receber
	add column conta_paga boolean not null default false;
	
update conta_receber set
	conta_paga = ((data_pagamento is not null) and (valor_pago is not null));
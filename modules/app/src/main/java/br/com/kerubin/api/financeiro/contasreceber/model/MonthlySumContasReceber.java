package br.com.kerubin.api.financeiro.contasreceber.model;

import lombok.Data;

@Data
public class MonthlySumContasReceber {
	
	private MonthlySum apagar;
	private MonthlySum pago;
	
	public MonthlySumContasReceber(MonthlySum apagar, MonthlySum pago) {
		this.apagar = apagar;
		this.pago = pago;
	}
	
	public MonthlySumContasReceber() {
		apagar = new MonthlySum();
		pago = new MonthlySum();
	}

}

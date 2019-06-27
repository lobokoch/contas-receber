package br.com.kerubin.api.financeiro.contasreceber.service;

import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberSituacaoDoAnoSum;
import br.com.kerubin.api.financeiro.contasreceber.model.MonthlySumContasReceber;

public interface ContasReceberDashboardService {

	MonthlySumContasReceber getMonthlySumContasReceber();

	ContasReceberSituacaoDoAnoSum getContasReceberSituacaoDoAno();

}

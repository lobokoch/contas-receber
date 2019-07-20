package br.com.kerubin.api.financeiro.contasreceber.service;

import java.util.List;

import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberHojeResumo;
import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberHojeResumoCompleto;
import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberSituacaoDoAnoSum;
import br.com.kerubin.api.financeiro.contasreceber.model.MonthlySumContasReceber;

public interface ContasReceberDashboardService {

	MonthlySumContasReceber getMonthlySumContasReceber();

	ContasReceberSituacaoDoAnoSum getContasReceberSituacaoDoAno();

	List<ContasReceberHojeResumo> getContasReceberHojeResumo();

	ContasReceberHojeResumoCompleto getContasReceberHojeResumoCompleto();

}

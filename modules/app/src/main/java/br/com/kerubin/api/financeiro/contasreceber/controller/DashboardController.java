package br.com.kerubin.api.financeiro.contasreceber.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberHojeResumo;
import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberHojeResumoCompleto;
import br.com.kerubin.api.financeiro.contasreceber.model.ContasReceberSituacaoDoAnoSum;
import br.com.kerubin.api.financeiro.contasreceber.model.MonthlySumContasReceber;
import br.com.kerubin.api.financeiro.contasreceber.service.ContasReceberDashboardService;

@RestController
@RequestMapping("financeiro/contas_receber/dashboard")
public class DashboardController {
	
	@Inject
	private ContasReceberDashboardService contasReceberDashboardService;
	
	@GetMapping("/getMonthlySumContasReceber")
	public MonthlySumContasReceber getMonthlySumContasReceber() {
		MonthlySumContasReceber result = contasReceberDashboardService.getMonthlySumContasReceber();
		return result;
	}
	
	@GetMapping("/getContasReceberSituacaoDoAno")
	public ContasReceberSituacaoDoAnoSum getContasReceberSituacaoDoAno() {
		ContasReceberSituacaoDoAnoSum result = contasReceberDashboardService.getContasReceberSituacaoDoAno();
		return result;
	}
	
	@GetMapping("/getContasReceberHojeResumo")
	public List<ContasReceberHojeResumo> getContasReceberHojeResumo() {
		List<ContasReceberHojeResumo> result = contasReceberDashboardService.getContasReceberHojeResumo();
		return result;
	}
	
	@GetMapping("/getContasReceberHojeResumoCompleto")
	public ContasReceberHojeResumoCompleto getContasReceberHojeResumoCompleto() {
		ContasReceberHojeResumoCompleto result = contasReceberDashboardService.getContasReceberHojeResumoCompleto();
		return result;
	}

}

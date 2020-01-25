/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;


import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import br.com.kerubin.api.financeiro.contasreceber.FormaPagamento;

public class ContaReceberListFilter {

	private java.util.List<String> descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate dataVencimentoFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate dataVencimentoTo;
	
	private java.math.BigDecimal valorFrom;
	
	private java.math.BigDecimal valorTo;
	
	private FormaPagamento formaPagamento;
	
	private Boolean dataPagamentoIsNotNull;
	
	private Boolean dataPagamentoIsNull;
	
	private Boolean idConcBancariaIsNotNull;
	
	private java.util.List<String> histConcBancaria;
	
	private java.util.List<String> agrupador;
	
	// Map field for developer customizing parameters.
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getDescricao() {
		return descricao;
	}
	
	public void setDescricao(java.util.List<String> descricao) {
		this.descricao = descricao;
	}
	
	public java.time.LocalDate getDataVencimentoFrom() {
		return dataVencimentoFrom;
	}
	
	public void setDataVencimentoFrom(java.time.LocalDate dataVencimentoFrom) {
		this.dataVencimentoFrom = dataVencimentoFrom;
	}
	
	public java.time.LocalDate getDataVencimentoTo() {
		return dataVencimentoTo;
	}
	
	public void setDataVencimentoTo(java.time.LocalDate dataVencimentoTo) {
		this.dataVencimentoTo = dataVencimentoTo;
	}
	
	public java.math.BigDecimal getValorFrom() {
		return valorFrom;
	}
	
	public void setValorFrom(java.math.BigDecimal valorFrom) {
		this.valorFrom = valorFrom;
	}
	
	public java.math.BigDecimal getValorTo() {
		return valorTo;
	}
	
	public void setValorTo(java.math.BigDecimal valorTo) {
		this.valorTo = valorTo;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
			
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Boolean isDataPagamentoIsNotNull() {
		return dataPagamentoIsNotNull != null && dataPagamentoIsNotNull;
	}
	
	public Boolean getDataPagamentoIsNotNull() {
		return dataPagamentoIsNotNull;
	}
	
	public void setDataPagamentoIsNotNull(Boolean dataPagamentoIsNotNull) {
		this.dataPagamentoIsNotNull = dataPagamentoIsNotNull;
	}
	
	public Boolean isDataPagamentoIsNull() {
		return dataPagamentoIsNull != null && dataPagamentoIsNull;
	}
	
	public Boolean getDataPagamentoIsNull() {
		return dataPagamentoIsNull;
	}
	
	public void setDataPagamentoIsNull(Boolean dataPagamentoIsNull) {
		this.dataPagamentoIsNull = dataPagamentoIsNull;
	}
	
	
	public Boolean isIdConcBancariaIsNotNull() {
		return idConcBancariaIsNotNull != null && idConcBancariaIsNotNull;
	}
	
	public Boolean getIdConcBancariaIsNotNull() {
		return idConcBancariaIsNotNull;
	}
			
	public void setIdConcBancariaIsNotNull(Boolean idConcBancariaIsNotNull) {
		this.idConcBancariaIsNotNull = idConcBancariaIsNotNull;
	}
	
	public java.util.List<String> getHistConcBancaria() {
		return histConcBancaria;
	}
	
	public void setHistConcBancaria(java.util.List<String> histConcBancaria) {
		this.histConcBancaria = histConcBancaria;
	}
	
	public java.util.List<String> getAgrupador() {
		return agrupador;
	}
	
	public void setAgrupador(java.util.List<String> agrupador) {
		this.agrupador = agrupador;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}

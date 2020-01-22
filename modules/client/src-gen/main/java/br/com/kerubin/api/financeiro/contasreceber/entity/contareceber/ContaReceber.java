/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:33.911
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaLookupResult;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.financeiro.contasreceber.FormaPagamento;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteLookupResult;

public class ContaReceber {

	private java.util.UUID id;
	
	@NotNull(message="\"Plano de contas\" é obrigatório.")
	private PlanoContaLookupResult planoContas;
	
	@NotBlank(message="\"Descrição da conta\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição da conta\" pode ter no máximo 255 caracteres.")
	private String descricao;
	
	@NotNull(message="\"Vencimento\" é obrigatório.")
	private java.time.LocalDate dataVencimento;
	
	@NotNull(message="\"Valor da conta\" é obrigatório.")
	private java.math.BigDecimal valor;
	
	@NotNull(message="\"Forma de pagamento\" é obrigatório.")
	private FormaPagamento formaPagamento;
	
	private ContaBancariaLookupResult contaBancaria;
	
	private CartaoCreditoLookupResult cartaoCredito;
	
	@Size(max = 255, message = "\"Dados complementares\" pode ter no máximo 255 caracteres.")
	private String outrosDescricao;
	
	private java.time.LocalDate dataPagamento;
	
	private java.math.BigDecimal valorDesconto;
	
	private java.math.BigDecimal valorMulta;
	
	private java.math.BigDecimal valorJuros;
	
	private java.math.BigDecimal valorAcrescimos;
	
	private java.math.BigDecimal valorPago;
	
	private ClienteLookupResult cliente;
	
	@Size(max = 255, message = "\"Documento\" pode ter no máximo 255 caracteres.")
	private String numDocumento;
	
	@Size(max = 255, message = "\"Id da conciliação bancária\" pode ter no máximo 255 caracteres.")
	private String idConcBancaria;
	
	@Size(max = 255, message = "\"Histórico da conciliação bancária\" pode ter no máximo 255 caracteres.")
	private String histConcBancaria;
	
	@Size(max = 255, message = "\"Documento da conciliação bancária\" pode ter no máximo 255 caracteres.")
	private String numDocConcBancaria;
	
	@Size(max = 1000, message = "\"Observações\" pode ter no máximo 1000 caracteres.")
	private String observacoes;
	
	@Size(max = 255, message = "\"Identificador para agrupamento da conta\" pode ter no máximo 255 caracteres.")
	private String agrupador;
	
	@Size(max = 255, message = "\"Criado por\" pode ter no máximo 255 caracteres.")
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	@Size(max = 255, message = "\"Alterado por\" pode ter no máximo 255 caracteres.")
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	
	public ContaReceber() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public PlanoContaLookupResult getPlanoContas() {
		return planoContas;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public java.time.LocalDate getDataVencimento() {
		return dataVencimento;
	}
	
	public java.math.BigDecimal getValor() {
		return valor;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public ContaBancariaLookupResult getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoLookupResult getCartaoCredito() {
		return cartaoCredito;
	}
	
	public String getOutrosDescricao() {
		return outrosDescricao;
	}
	
	public java.time.LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public java.math.BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	
	public java.math.BigDecimal getValorMulta() {
		return valorMulta;
	}
	
	public java.math.BigDecimal getValorJuros() {
		return valorJuros;
	}
	
	public java.math.BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}
	
	public java.math.BigDecimal getValorPago() {
		return valorPago;
	}
	
	public ClienteLookupResult getCliente() {
		return cliente;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public String getIdConcBancaria() {
		return idConcBancaria;
	}
	
	public String getHistConcBancaria() {
		return histConcBancaria;
	}
	
	public String getNumDocConcBancaria() {
		return numDocConcBancaria;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public String getAgrupador() {
		return agrupador;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public java.time.LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public java.time.LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setPlanoContas(PlanoContaLookupResult planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setDataVencimento(java.time.LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public void setValor(java.math.BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void setContaBancaria(ContaBancariaLookupResult contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoLookupResult cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setOutrosDescricao(String outrosDescricao) {
		this.outrosDescricao = outrosDescricao;
	}
	
	public void setDataPagamento(java.time.LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public void setValorDesconto(java.math.BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public void setValorMulta(java.math.BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	
	public void setValorJuros(java.math.BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}
	
	public void setValorAcrescimos(java.math.BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}
	
	public void setValorPago(java.math.BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	public void setCliente(ClienteLookupResult cliente) {
		this.cliente = cliente;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public void setIdConcBancaria(String idConcBancaria) {
		this.idConcBancaria = idConcBancaria;
	}
	
	public void setHistConcBancaria(String histConcBancaria) {
		this.histConcBancaria = histConcBancaria;
	}
	
	public void setNumDocConcBancaria(String numDocConcBancaria) {
		this.numDocConcBancaria = numDocConcBancaria;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void setCreatedDate(java.time.LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public void setLastModifiedDate(java.time.LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaReceber other = (ContaReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}

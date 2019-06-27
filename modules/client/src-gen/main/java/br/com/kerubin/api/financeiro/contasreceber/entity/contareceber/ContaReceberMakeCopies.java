/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaReceberMakeCopies {
	
	@NotNull(message="'Id' é obrigatório.")
	private java.util.UUID id;
	
	@Min(value = 1, message = "A quantidade de cópias não pode ser menor que 1.")
	@Max(value = 60, message = "A quantidade de cópias não pode ser maior que 60.")
	private Long numberOfCopies;
	
	@Min(value = 1, message = "O intervalo não pode ser menor que 1.")
	@Max(value = 1000, message = "O intervalo não pode ser maior que 1000.")
	private Long referenceFieldInterval;
	
	@NotBlank(message = "O campo 'Agrupador' deve ser informado.")
	private String agrupador;;
	
	public ContaReceberMakeCopies() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	public java.util.UUID getId() {
		return id;
	}
	
	public Long getNumberOfCopies() {
		return numberOfCopies;
	}
	
	public Long getReferenceFieldInterval() {
		return referenceFieldInterval;
	}
	
	public String getAgrupador() {
		return agrupador;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNumberOfCopies(Long numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	public void setReferenceFieldInterval(Long referenceFieldInterval) {
		this.referenceFieldInterval = referenceFieldInterval;
	}
	
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

}

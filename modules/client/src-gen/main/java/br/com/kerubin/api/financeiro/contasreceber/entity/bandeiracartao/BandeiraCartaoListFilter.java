/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao;


import java.util.HashMap;
import java.util.Map;


public class BandeiraCartaoListFilter {

	private java.util.List<String> nomeBandeira;
	
	// Map field for developer customizing parameters.
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getNomeBandeira() {
		return nomeBandeira;
	}
	
	public void setNomeBandeira(java.util.List<String> nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}

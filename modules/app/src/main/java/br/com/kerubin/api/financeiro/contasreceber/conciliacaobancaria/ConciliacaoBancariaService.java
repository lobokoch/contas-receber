package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import java.util.List;

import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;

public interface ConciliacaoBancariaService {

	ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO);
	
	List<ContaReceberEntity> discardNotStartsWithTokens(List<ContaReceberEntity> contas, List<String> tokens);

}

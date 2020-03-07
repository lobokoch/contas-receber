package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import java.util.List;
import java.util.Map;

import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;

public interface ConciliacaoBancariaService {

	ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO);
	
	Map<ContaReceberEntity, Integer> computeScore(List<ContaReceberEntity> contas, List<String> tokens, ConciliacaoTransacaoDTO transacao);

}

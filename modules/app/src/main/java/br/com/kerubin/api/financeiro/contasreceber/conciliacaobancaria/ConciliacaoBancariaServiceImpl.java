package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatMoney;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEquals;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getTokens;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getDiff;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isZero;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isLt;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isGte;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.contasreceber.FormaPagamento;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberService;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.QContaReceberEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciliacaoBancariaServiceImpl implements ConciliacaoBancariaService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private ContaReceberService contaReceberService;
	
	@Inject
	private ContaReceberRepository contaReceberRepository;
	
	@Inject
	private ContaBancariaRepository contaBancariaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QContaReceberEntity qContaReceber = QContaReceberEntity.contaReceberEntity;
		
		Map<String, ContaReceberEntity> lastVisitedList = new HashMap<>();
		
		conciliacaoBancariaDTO.getTransacoes().forEach(transacao -> {
			
			List<String> tokens = getTokens(transacao.getTrnHistorico());
			BooleanBuilder filtroTokens = new BooleanBuilder();
			if (isNotEmpty(tokens)) {
				tokens.forEach(token -> filtroTokens.or(qContaReceber.descricao.containsIgnoreCase(token)));			
			}
			
			BooleanBuilder filtroDados = new BooleanBuilder();
			filtroDados
			.and(qContaReceber.idConcBancaria.eq(transacao.getTrnId()))
			.or(qContaReceber.valor.eq(transacao.getTrnValor()))
			.or(filtroTokens); // Faz match com os tokens do histórico do extrato com as descrições das contas.
			
			LocalDate dataToRef = transacao.getTrnData();
			String key = getTrnKey(transacao);
			ContaReceberEntity lastVisited = lastVisitedList.get(key);
			boolean hasLastVisited = isNotEmpty(lastVisited);
			boolean lastVisitedIsAfter = hasLastVisited && lastVisited.getDataVencimento().isAfter(dataToRef);
			if (lastVisitedIsAfter) {
				dataToRef = lastVisited.getDataVencimento();
			}
			
			LocalDate from = transacao.getTrnData().minusMonths(1);
			LocalDate to = dataToRef.plusMonths(1);
			
			BooleanBuilder filtroPerido = new BooleanBuilder();
			// A data da transação é igual a data de pagamento da conta
			filtroPerido.and(qContaReceber.dataPagamento.eq(transacao.getTrnData()))
			// A conta não está paga ainda e a data da transação está no intervalo do vencimento.
			.or(qContaReceber.dataPagamento.isNull().and(qContaReceber.dataVencimento.between(from, to)));
			// filtroPerido.and(qContaPagar.dataVencimento.between(from, to)).or(qContaPagar.dataPagamento.between(from, to));
			
			BooleanBuilder where = new BooleanBuilder();
			where.and(filtroDados).and(filtroPerido);
			
			List<ContaReceberEntity> contas = query
					.selectFrom(qContaReceber)
					.where(where)
					.orderBy(qContaReceber.dataVencimento.asc())
					.fetch();
			
			if (isNotEmpty(contas)) {
				
				Comparator<ContaReceberEntity> comparator = Comparator.comparing(ContaReceberEntity::getDataVencimento);
				ContaReceberEntity contaMaiorDataVencimento = contas.stream().max(comparator).get();
				
				ContaReceberEntity contaCandidata = null;
				boolean temAlgumaContaComConciliacao = contas.stream().anyMatch(it -> isConciliado(it, transacao)); 
				if (temAlgumaContaComConciliacao || !lastVisitedIsAfter) {
					List<ContaReceberEntity> contasClone = new ArrayList<>(contas);
					
					// Tem alguma conta conciliada?
					contaCandidata = contas.stream().filter(it -> isConciliado(it, transacao)).findFirst().orElse(null);
					
					// Tem alguma conta paga?
					if (isEmpty(contaCandidata)) {
						contaCandidata = contas.stream().filter(it -> isPago(it, transacao)).findFirst().orElse(null);
					}
					
					// Tem alguma conta em aberto?
					if (isEmpty(contaCandidata)) {
						//if (hasLastVisited) { // Não deveria pegar a mais próxima e sim a última, pois a mais próxima já deve ter sido pega.
						//	contaCandidata = contaMaiorDataVencimento;
						//}
						//else {
							contaCandidata = contas.stream().filter(it -> isEmAberto(it, transacao, contasClone)).findFirst().orElse(null);
						//}
					}
					
					if (isEmpty(contaCandidata)) {
						// Os títulos devem ter valores diferentes da transação, então procura a conta de valor mais próximo.
						contaCandidata = getContaEmAbertoComValorMaisPerto(transacao.getTrnValor(), contasClone);
					}
					
					if (isEmpty(contaCandidata)) {
						contaCandidata = contas.get(0);
					}
				}
				else {
					// Como tem contas em lastVisitedList, a de maior data encontrada é a candidata mais provável. Pagamentos antecipados.
					contaCandidata = contaMaiorDataVencimento;
				}
				
				lastVisitedList.put(key, contaMaiorDataVencimento);
				
				transacao.setTituloConciliadoId(contaCandidata.getId());
				transacao.setTituloConciliadoDesc(contaCandidata.getDescricao() + " (" + formatMoney(contaCandidata.getValor())  + ")");
				
				SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
				if (isNotEmpty(contaCandidata.getDataPagamento())) { // Já pagou, baixado.
					if (isNotEmpty(contaCandidata.getIdConcBancaria())) { // Pagamento normal, sem conciliação
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER;
					}
					else {
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONTAS_RECEBER_BAIXADO_SEM_CONCILIACAO;
					}
				}
				else {
					situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER;
				}
				transacao.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn);
				
				BigDecimal diff = getDiff(transacao.getTrnValor(), contaCandidata.getValor());
				if (isGte(diff, transacao.getTrnValor().divide(BigDecimal.valueOf(2))) || isGte(diff, contaCandidata.getValor().divide(BigDecimal.valueOf(2)))) {
					transacao.setConciliadoMsg("Valor da conta e o valor da transação possuem diferença maior ou igual a 50%");
				}
				
				// Se já foi conciliado, remove as contas que não tem a ver com a conta que conciliou.
				if (contas.size() > 1 && SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER.equals(situacaoConciliacaoTrn)) {
					contas.removeIf(it -> !it.getId().equals(transacao.getTituloConciliadoId()));
				}
				
				// Se já foi baixada, e tiver contas com a data de pagamento igual a data da transação, deixa apenas essas contas. 
				if (contas.size() > 1 && SituacaoConciliacaoTrn.CONTAS_RECEBER_BAIXADO_SEM_CONCILIACAO.equals(situacaoConciliacaoTrn)) {
					List<ContaReceberEntity> contasCandidatas = contas
							.stream()
							.filter(it -> isNotEmpty(it.getDataPagamento()) && it.getDataPagamento().equals(transacao.getTrnData()))
							.collect(Collectors.toList());
					
					if (!contasCandidatas.isEmpty()) {
						contas.removeIf(it1 -> !contasCandidatas.stream().anyMatch(it2 -> it2.getId().equals(it1.getId())));
					}
					
				}
				
				// Caso tenha mais de um título, empacota eles junto para o usuário decidir qual é o título certo.
				if (!contas.isEmpty()) {
					
					List<ConciliacaoTransacaoTituloDTO> titulos = contas.stream().map(it -> {
						ConciliacaoTransacaoTituloDTO titulo = ConciliacaoTransacaoTituloDTO.builder()
								.tituloConciliadoId(it.getId())
								.tituloConciliadoDesc(it.getDescricao() + " (" + formatMoney(it.getValor())  + ")")
								.tituloConciliadoDataVen(it.getDataVencimento())
								.tituloConciliadoDataPag(it.getDataPagamento())
								.build();
						
						// Situação do título
						SituacaoConciliacaoTrn situacaoConciliacaoTrn2 = titulo.getSituacaoConciliacaoTrn(); // Valor atual é o default.
						if (isNotEmpty(it.getDataPagamento())) { // Já pagou, baixado.
							if (isNotEmpty(it.getIdConcBancaria())) { // Pagamento normal, sem conciliação
								situacaoConciliacaoTrn2 = SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER;
							}
							else {
								situacaoConciliacaoTrn2 = SituacaoConciliacaoTrn.CONTAS_RECEBER_BAIXADO_SEM_CONCILIACAO;
							}
						}
						else {
							situacaoConciliacaoTrn2 = SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER;
						}
						titulo.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn2);
						
						return titulo;
						
					}).collect(Collectors.toList());
					
					transacao.setConciliacaoTransacaoTitulosDTO(titulos);
					
				} // if (!contas.isEmpty())
				
			}//
			
		});
		
		return conciliacaoBancariaDTO;
	}
	
	private String getTrnKey(ConciliacaoTransacaoDTO transacao) {
		String key = transacao.getTrnValor().toString() + "_" + transacao.getTrnHistorico().toLowerCase().replaceAll(" ", "_");
		return key;
	}
	
	private boolean isConciliado(ContaReceberEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = transacao.getTrnId().equals(conta.getIdConcBancaria());
		return result;
	}
	
	private boolean isEmAberto(ContaReceberEntity conta, ConciliacaoTransacaoDTO transacao,
			List<ContaReceberEntity> contas) {
		ContaReceberEntity contaMaisPerto = getContaEmAbertoComDataVencimentoMaisPerto(transacao.getTrnData(), contas);
		
		boolean result = isEquals(transacao.getTrnValor(), conta.getValor()) && //
				isEmpty(conta.getDataPagamento()) && //
				(isEmpty(contaMaisPerto) || conta.equals(contaMaisPerto));
		return result;
	}
	
	public ContaReceberEntity getContaEmAbertoComDataVencimentoMaisPerto(LocalDate dataRef, List<ContaReceberEntity> contas) {
		if (isEmpty(contas)) {
			return null;
		}
		
		contas = contas.stream().filter(it -> isEmpty(it.getDataPagamento())).collect(Collectors.toList());
		if (isEmpty(contas)) {
			return null;
		}
		
		ContaReceberEntity result = contas.get(0);
		int dif = Math.abs(Period.between(dataRef, result.getDataVencimento()).getDays());
		if (dif == 0) { // dataRef = dataVencimento, já retorna
			return result;
		}
		
		int minDif = dif;
		for (int i = 1; i < contas.size(); i++) {
			ContaReceberEntity conta =  contas.get(i);
			dif = Math.abs(Period.between(dataRef, conta.getDataVencimento()).getDays());
			
			if (dif == 0) { // dataRef = dataVencimento, já retorna
				return result;
			}
			
			if (dif < minDif) {
				minDif = dif;
				result = conta;
			}
		}
		
		return result;
	}
	
	public ContaReceberEntity getContaEmAbertoComValorMaisPerto(BigDecimal valorRef, List<ContaReceberEntity> contas) {
		if (isEmpty(contas)) {
			return null;
		}
		
		contas = contas.stream().filter(it -> isEmpty(it.getDataPagamento())).collect(Collectors.toList());
		if (isEmpty(contas)) {
			return null;
		}
		
		ContaReceberEntity result = contas.get(0);
		BigDecimal dif = getDiff(result.getValor(), valorRef);
		if (isZero(dif)) { // valorRef == valor, já retorna
			return result;
		}
		
		BigDecimal minDif = dif;
		for (int i = 1; i < contas.size(); i++) {
			ContaReceberEntity conta = contas.get(i);
			dif = getDiff(conta.getValor(), valorRef);
			
			if (isZero(dif)) { // valorRef == valor, já retorna
				return result;
			}
			
			if (isLt(dif, minDif)) {
				minDif = dif;
				result = conta;
			}
		}
		
		return result;
	}

	private boolean isPago(ContaReceberEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = isEquals(transacao.getTrnValor(), conta.getValorPago()) && // Valor é igual e...
				isNotEmpty(conta.getDataPagamento()) && 
				transacao.getTrnData().equals(conta.getDataPagamento()); // a data da transação é igual a data de pagamento da conta.
		return result;
	}
	
	//@Transactional
	@Override
	public ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		log.info("INICIO aplicarConciliacaoBancaria...");
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		List<ConciliacaoTransacaoDTO> transacoes = conciliacaoBancariaDTO.getTransacoes(); 
		
		String bancoId = conciliacaoBancariaDTO.getBancoId();
		String agenciaId = conciliacaoBancariaDTO.getAgenciaId();
		String contaBancariaId = conciliacaoBancariaDTO.getContaId();
		
		String erroMsg = null;
		
		ContaBancariaEntity contaBancariaEntity = contaBancariaRepository.findByNumeroContaAndAgenciaNumeroAgenciaAndAgenciaBancoNumero(contaBancariaId, agenciaId, bancoId);
		if (isEmpty(contaBancariaEntity)) {
			erroMsg = MessageFormat.format("Conta bancária não encontrada para bancoId:{0}, agenciaId:{1}, contaBancariaId:{2}", bancoId, agenciaId, contaBancariaId);
			log.error(erroMsg);
			
			final String msg = erroMsg; 
			transacoes.forEach(it -> {
				it.setConciliadoComErro(true);
				it.setConciliadoMsg(msg);
			});
			
			return conciliacaoBancariaDTO;
		}
		
		for (ConciliacaoTransacaoDTO transacao : transacoes) {
			erroMsg = null;
			String logHeader = MessageFormat.format("Conta id: {0}, doc: {1}, histórico: {2}", 
					transacao.getId(), transacao.getTrnId(), transacao.getTrnHistorico());
			
			if (isEmpty(transacao.getTituloConciliadoId())) {
				erroMsg = "Id do título está vazio para baixar conta via conciliação";
			}
			
			if (isEmpty(erroMsg) && isEmpty(transacao.getTrnValor())) {
				erroMsg = "Valor da transação está vazio para baixar conta via conciliação";
			}
			
			if (isEmpty(erroMsg) && isEmpty(transacao.getTrnData())) {
				erroMsg = "Data da transação está vazia para baixar conta via conciliação";
			}
			
			if (isEmpty(erroMsg) && !transacao.isCredito()) {
				erroMsg = format("Tipo da transação deveria ser CRÉDITO mas é: {0}", transacao.getTrnTipo());
			}
			
			if (isNotEmpty(erroMsg)) {
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			ContaReceberEntity conta = contaReceberRepository.findById(transacao.getTituloConciliadoId()).orElse(null);
			
			if (isEmpty(conta)) {
				erroMsg = "Título não localizado com o id: " + transacao.getTituloConciliadoId();
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			// Valida algumas coisas da conta
			if (isNotEmpty(conta.getDataPagamento())) {
				erroMsg = format("Conta já baixada em: {0}, doc: {1}", conta.getDataPagamento(), conta.getIdConcBancaria());
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			if (!isEquals(conta.getValor(), transacao.getTrnValor())) {
				erroMsg = format("Valor da conta: {0}, diverge do valor da transação de conciliação: {1}", conta.getValor(), transacao.getTrnValor());
				log.warn(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(false); // Não é um erro, só um warning.
				transacao.setConciliadoMsg(erroMsg);
			}
			
			conta.setDataPagamento(transacao.getTrnData());
			conta.setValorPago(transacao.getTrnValor());
			conta.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
			conta.setContaBancaria(contaBancariaEntity);
			conta.setIdConcBancaria(transacao.getTrnId());
			conta.setNumDocConcBancaria(transacao.getTrnDocumento());
			conta.setHistConcBancaria(transacao.getTrnHistorico());
			
			String obs = isNotEmpty(conta.getObservacoes()) ? conta.getObservacoes() : "";
			obs = "*** Histórico: baixa via conciliação bancária:" +  transacao.getTrnHistorico() + "\r\n" + obs;
			conta.setObservacoes(obs);
			
			try {
				conta = contaReceberService.update(conta.getId(), conta);
				
				transacao.setConciliadoComErro(false);
				transacao.setSituacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER);
				transacao.setDataConciliacao(LocalDate.now());
				transacao.setTituloConciliadoDesc(conta.getDescricao());
			} catch (Exception e) {
				log.error("Erro ao baixar a conta a pagar via conciliação bancária:" + conta.getId(), e);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(e.getMessage());
			}
		}
		
		log.info("FIM aplicarConciliacaoBancaria...");
		
		return conciliacaoBancariaDTO;
	}
	
	

}

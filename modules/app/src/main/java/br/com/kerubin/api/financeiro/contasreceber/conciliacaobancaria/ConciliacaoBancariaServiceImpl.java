package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEquals;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

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
	
	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QContaReceberEntity qContaReceber = QContaReceberEntity.contaReceberEntity;
		
		conciliacaoBancariaDTO.getTransacoes().forEach(transacao -> {
			
			BooleanBuilder filtroDados = new BooleanBuilder();
			filtroDados
			.and(qContaReceber.numDocConcBancaria.eq(transacao.getTrnDocumento()))
			.or(qContaReceber.valor.eq(transacao.getTrnValor()))
			.or(qContaReceber.valorPago.eq(transacao.getTrnValor()));
			
			LocalDate from = transacao.getTrnData().minusDays(30);
			LocalDate to = transacao.getTrnData().plusDays(30);
			
			BooleanBuilder filtroPerido = new BooleanBuilder();
			filtroPerido.and(qContaReceber.dataVencimento.between(from, to)).or(qContaReceber.dataPagamento.between(from, to));
			
			BooleanBuilder where = new BooleanBuilder();
			where.and(filtroDados).and(filtroPerido);
			
			List<ContaReceberEntity> contas = query
					.selectFrom(qContaReceber)
					.where(where)
					.orderBy(qContaReceber.dataVencimento.asc())
					.fetch();
			
			if (isNotEmpty(contas)) {
				ContaReceberEntity contaCandidata = contas.stream().filter(conta -> isConciliado(conta, transacao)).findFirst().orElse(null);
				
				// Preferência para conta em aberto.
				if (isEmpty(contaCandidata)) {
					contaCandidata = contas.stream().filter(conta -> isEmAberto(conta, transacao)).findFirst().orElse(null);
				}
				
				if (isEmpty(contaCandidata)) {
					contaCandidata = contas.stream().filter(conta -> isPago(conta, transacao)).findFirst().orElse(null);
				}
				
				if (isNotEmpty(contaCandidata)) {
					transacao.setTituloConciliadoId(contaCandidata.getId());
					transacao.setTituloConciliadoDesc(contaCandidata.getDescricao());
					
					SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
					if (isNotEmpty(contaCandidata.getDataPagamento())) { // Já pagou, baixado.
						if (isNotEmpty(contaCandidata.getNumDocConcBancaria())) { // Pagamento normal, sem conciliação
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
				}
			}
			
		});
		
		return conciliacaoBancariaDTO;
	}
	
	private boolean isConciliado(ContaReceberEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = transacao.getTrnDocumento().equals(conta.getNumDocConcBancaria());
		return result;
	}
	
	private boolean isEmAberto(/*List<ContaPagarEntity> contas, */ContaReceberEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = isEquals(transacao.getTrnValor(), conta.getValor()) && isEmpty(conta.getDataPagamento());
		return result;
	}
	
	private boolean isPago(/*List<ContaPagarEntity> contas, */ContaReceberEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = isEquals(transacao.getTrnValor(), conta.getValorPago()) && isNotEmpty(conta.getDataPagamento());
		return result;
	}
	
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
					transacao.getId(), transacao.getTrnDocumento(), transacao.getTrnHistorico());
			
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
				erroMsg = format("Conta já baixada em: {0}, doc: {1}", conta.getDataPagamento(), conta.getNumDocConcBancaria());
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			if (!isEquals(conta.getValor(), transacao.getTrnValor())) {
				erroMsg = format("Valor da conta: {0}, diverge do valor da transação de conciliação: {1}", conta.getValor(), transacao.getTrnValor());
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			conta.setDataPagamento(transacao.getTrnData());
			conta.setValorPago(transacao.getTrnValor());
			conta.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
			conta.setContaBancaria(contaBancariaEntity);
			conta.setNumDocConcBancaria(transacao.getTrnDocumento());
			conta.setHistConcBancaria(transacao.getTrnHistorico());
			
			String obs = isNotEmpty(conta.getObservacoes()) ? conta.getObservacoes() : "";
			obs = "*** Histórico baixa via conciliação bancária:" +  transacao.getTrnHistorico() + "\r\n" + obs;
			conta.setObservacoes(obs);
			
			try {
				contaReceberService.update(conta.getId(), conta);
				
				transacao.setConciliadoComErro(false);
				transacao.setConciliadoMsg("Sucesso");
				transacao.setSituacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER);
				transacao.setDataConciliacao(LocalDate.now());
				transacao.setTituloConciliadoDesc(conta.getDescricao());
			} catch (Exception e) {
				log.error("Erro ao baixar a conta a pagar via conciliação banácia:" + conta.getId(), e);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(e.getMessage());
			}
		}
		
		log.info("FIM aplicarConciliacaoBancaria...");
		
		return conciliacaoBancariaDTO;
	}
	
	

}

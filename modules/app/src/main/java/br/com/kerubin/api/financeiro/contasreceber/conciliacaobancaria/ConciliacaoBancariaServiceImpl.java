package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.QContaReceberEntity;
import br.com.kerubin.api.servicecore.util.CoreUtils;


@Service
public class ConciliacaoBancariaServiceImpl implements ConciliacaoBancariaService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (conciliacaoBancariaDTO == null || conciliacaoBancariaDTO.getTransacoes() == null) {
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
			
			List<ContaReceberEntity> contas = query.selectFrom(qContaReceber).where(where).fetch();
			
			if (contas != null && !contas.isEmpty()) {
				ContaReceberEntity contaCandidata = contas.stream().filter(conta -> transacao.getTrnDocumento().equals(conta.getNumDocConcBancaria())).findFirst().orElse(null);
				
				if (contaCandidata == null) {
					contaCandidata = contas.stream().filter(conta -> CoreUtils.isEquals(transacao.getTrnValor(), conta.getValorPago())).findFirst().orElse(null);
				}
				
				if (contaCandidata == null) {
					contaCandidata = contas.stream().filter(conta -> CoreUtils.isEquals(transacao.getTrnValor(), conta.getValor())).findFirst().orElse(null);
				}
				
				if (contaCandidata != null) {
					transacao.setTituloConciliadoId(contaCandidata.getId());
					transacao.setTituloConciliadoDesc(contaCandidata.getDescricao());
					
					SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
					if (contaCandidata.getDataPagamento() != null) { // Já pago, baixado.
						if (contaCandidata.getNumDocConcBancaria() != null) { // Pagamento normal, sem conciliação
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

}

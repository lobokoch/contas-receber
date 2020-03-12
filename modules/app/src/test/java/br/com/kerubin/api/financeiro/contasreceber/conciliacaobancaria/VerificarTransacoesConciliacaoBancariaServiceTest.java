/**********************************************************************************************
Code generated with MKL Plug-in version: 27.0.12
Code generated at time stamp: 2019-11-06T06:23:00.711
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.kerubin.api.financeiro.contasreceber.FormaPagamento;
import br.com.kerubin.api.financeiro.contasreceber.TipoContaBancaria;
import br.com.kerubin.api.financeiro.contasreceber.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.contasreceber.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberDTOConverter;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberListFilterPredicate;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberService;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberServiceImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaListFilterPredicate;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaService;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaServiceImpl;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.servicecore.util.CoreUtils;


@RunWith(SpringRunner.class)
public class VerificarTransacoesConciliacaoBancariaServiceTest extends FinanceiroContasReceberBaseEntityTest {
	
	private static final String BANCO_ID = "237";
	private static final String AGENDIA_ID = "12345";
	private static final String CONTA_BANCARIA_ID = "98765";
	private static final BigDecimal VALOR = new BigDecimal("1269.4");
	
	@TestConfiguration
	static class ContaReceberServiceTestConfig {
		
		@Bean
		public ContaReceberListFilterPredicate contaReceberListFilterPredicate() {
			return new ContaReceberListFilterPredicateImpl();
		}
		
		@Bean
		public ContaReceberService contaReceberService() {
			return new ContaReceberServiceImpl();
		}
		
		@Bean
		public ContaReceberDTOConverter contaReceberDTOConverter() {
			return new ContaReceberDTOConverter();
		}
		
		@Bean
		public ConciliacaoBancariaService conciliacaoBancariaService() {
			return new ConciliacaoBancariaServiceImpl();
		}
		
		@Bean
		public PlanoContaService planoContaService() {
			return new PlanoContaServiceImpl(); 
		}
		
		@Bean
		public PlanoContaListFilterPredicate planoContaListFilterPredicate() {
			return new PlanoContaListFilterPredicateImpl();
		}
		
	}
	
	
	@Inject
	protected ContaReceberService contaReceberService;
	
	@Inject
	protected ContaReceberDTOConverter contaReceberDTOConverter;
	
	@Inject
	protected PlanoContaRepository planoContaRepository;
	
	@Inject
	protected ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	protected CartaoCreditoRepository cartaoCreditoRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	@Inject
	private ConciliacaoBancariaService conciliacaoBancariaService;
	
	@Test
	public void testScores() {
		
		List<ContaReceberEntity> contas = new ArrayList<>(); 
		
		ContaReceberEntity c = new ContaReceberEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		c.setValorPago(BigDecimal.valueOf(181.50));
		
		ClienteEntity cliente = new ClienteEntity();
		cliente.setNome("Celesc Santa Catarina");
		c.setCliente(cliente);
		
		ConciliacaoTransacaoDTO transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.50));
		
		contas.add(c);
		
		List<String> tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		Map<ContaReceberEntity, Integer> scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(9462);
		
		contas.clear();
		
		c = new ContaReceberEntity();
		c.setDescricao("Conta de gáz");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		c.setValorPago(BigDecimal.valueOf(181.50));
		
		cliente = new ClienteEntity();
		cliente.setNome("Petrobras");
		c.setCliente(cliente);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.99));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(0);
		
		contas.clear();
		
		c = new ContaReceberEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		c.setValorPago(BigDecimal.valueOf(181.50));
		
		cliente = new ClienteEntity();
		cliente.setNome("Celesc Santa Catarina");
		c.setCliente(cliente);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.99));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(5);
		
		contas.clear();
		
		c = new ContaReceberEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		//c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		//c.setValorPago(BigDecimal.valueOf(181.50));
		
		cliente = new ClienteEntity();
		cliente.setNome("Celesc Santa Catarina");
		c.setCliente(cliente);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.50));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(8465);
		
		contas.clear();
		
		c = new ContaReceberEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 25));
		//c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		//c.setValorPago(BigDecimal.valueOf(181.50));
		
		cliente = new ClienteEntity();
		cliente.setNome("Celesc Santa Catarina");
		c.setCliente(cliente);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.50));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(7924);
		
		
	}
	
	@Test
	public void testVerificarTransacoes_Primeira() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 6, 10))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta1 = contas.get(0);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER));
	}
	
	@Test
	public void testVerificarTransacoes2_PrimeiraPagaRetornaSegunda() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaReceberEntity conat1 = contas.get(0);
		conat1 = receberConta(conat1, LocalDate.of(2019, 6, 10));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 07, 9))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta2 = contas.get(1);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta2.getId(), conta2.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER));
	}
	
	@Test
	public void testVerificarTransacoes_RetornaConta3() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaReceberEntity conat1 = contas.get(0);
		conat1 = receberConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaReceberEntity conat2 = contas.get(1);
		conat2 = receberConta(conat2, LocalDate.of(2019, 7, 9));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 8, 7))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta3 = contas.get(2);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta3.getId(), conta3.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER));
	}
	
	@Test
	public void testVerificarTransacoes_RetornaConta4() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaReceberEntity conat1 = contas.get(0);
		conat1 = receberConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaReceberEntity conat2 = contas.get(1);
		conat2 = receberConta(conat2, LocalDate.of(2019, 7, 9));
		
		ContaReceberEntity conat3 = contas.get(2);
		conat3 = receberConta(conat3, LocalDate.of(2019, 8, 7));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 9, 9))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta4 = contas.get(3);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta4.getId(), conta4.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER));
	}
	
	@Test
	public void testVerificarTransacoes_RetornaConta5() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaReceberEntity conat1 = contas.get(0);
		conat1 = receberConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaReceberEntity conat2 = contas.get(1);
		conat2 = receberConta(conat2, LocalDate.of(2019, 7, 9));
		
		ContaReceberEntity conat3 = contas.get(2);
		conat3 = receberConta(conat3, LocalDate.of(2019, 8, 7));
		
		ContaReceberEntity conat4 = contas.get(3);
		conat4 = receberConta(conat4, LocalDate.of(2019, 9, 9));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 10, 7))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta5 = contas.get(4);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta5.getId(), conta5.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER));
	}
	
	@Test
	public void testVerificarTransacoes_Retorna5Contas() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 7, 4))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 8, 4))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t3 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 9, 4))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t4 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 10, 4))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t5 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 11, 4))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		transacoes.add(t2);
		transacoes.add(t3);
		transacoes.add(t4);
		transacoes.add(t5);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta1 = contas.get(0);
		ContaReceberEntity conta2 = contas.get(1);
		ContaReceberEntity conta3 = contas.get(2);
		ContaReceberEntity conta4 = contas.get(3);
		ContaReceberEntity conta5 = contas.get(4);
		
		assertThat(transacoes).hasSize(5)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, 
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(conta2.getId(), conta2.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(conta3.getId(), conta3.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(conta4.getId(), conta4.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(conta5.getId(), conta5.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				);
	}
	
	@Test
	public void testVerificarTransacoes_Retorna4ContasPagas() {
		List<ContaReceberEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaReceberEntity conat1 = contas.get(0);
		conat1 = receberConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaReceberEntity conat2 = contas.get(1);
		conat2.setIdConcBancaria("0123456789");
		conat2.setNumDocConcBancaria("0123456789");
		conat2 = receberConta(conat2, LocalDate.of(2019, 7, 9));
		
		ContaReceberEntity conat3 = contas.get(2);
		conat3 = receberConta(conat3, LocalDate.of(2019, 8, 7));
		
		ContaReceberEntity conat4 = contas.get(3);
		conat4 = receberConta(conat4, LocalDate.of(2019, 9, 9));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 6, 10))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 7, 9))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t3 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 8, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t4 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 9, 9))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t5 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 10, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		transacoes.add(t2);
		transacoes.add(t3);
		transacoes.add(t4);
		transacoes.add(t5);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaReceberEntity conta1 = contas.get(0);
		ContaReceberEntity conta2 = contas.get(1);
		ContaReceberEntity conta3 = contas.get(2);
		//ContaReceberEntity conta4 = contas.get(3);
		ContaReceberEntity conta5 = contas.get(4);
		
		assertThat(transacoes).hasSize(5)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONTAS_RECEBER_BAIXADO_SEM_CONCILIACAO),
				tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONTAS_RECEBER_BAIXADO_SEM_CONCILIACAO),
				tuple(conta2.getId(), conta2.getDescricao(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER),
				tuple(conta3.getId(), conta3.getDescricao(), SituacaoConciliacaoTrn.CONTAS_RECEBER_BAIXADO_SEM_CONCILIACAO),
				tuple(conta5.getId(), conta5.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				);
	}
	
	
	private ConciliacaoBancariaDTO newConciliacaoBancariaDTO() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		return conciliacaoBancariaDTO;
	}
	
	private ContaReceberEntity receberConta(ContaReceberEntity conta, LocalDate dataPagamento) {
		conta.setDataPagamento(dataPagamento);
		conta.setValorPago(conta.getValor());
		
		conta = em.persistAndFlush(conta);
		return conta;
	}
	
	
	// BEGIN TESTS DEPENDENCIES
	
	private List<ContaReceberEntity> criarContas() {
		int quantidade = 10;
		LocalDate dataInicial = LocalDate.of(2019, 7, 4);
		List<ContaReceberEntity> contas = criarContaReceber(quantidade, dataInicial, VALOR);
		assertThat(contas).hasSize(quantidade);
		assertThat(contas.get(0).getDataVencimento()).isEqualTo(LocalDate.of(2019, 7, 4));
		assertThat(contas.get(contas.size() - 1).getDataVencimento()).isEqualTo(LocalDate.of(2020, 4, 4));
		
		return contas;
	}
	
	protected List<ContaReceberEntity> criarContaReceber(int quantidade, LocalDate dataInicial, BigDecimal valor) {
		LocalDate dataVencimento = dataInicial;
		List<ContaReceberEntity> contas = new ArrayList<>(quantidade);
		for (int i = 1; i <= quantidade; i++) {
			ContaReceberEntity contaReceberEntity = new ContaReceberEntity();
			
			contaReceberEntity.setPlanoContas(newPlanoContaEntity());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataVencStr = dataVencimento.format(formatter);
			
			contaReceberEntity.setDescricao("Conta número: " + i + ", vencimento: " + dataVencStr);
			contaReceberEntity.setDataVencimento(dataVencimento);
			
			contaReceberEntity.setValor(valor);
			contaReceberEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
			
			contaReceberEntity.setContaBancaria(newContaBancariaConciliacao());
			
			//contaReceberEntity.setCartaoCredito(null);
			//contaReceberEntity.setOutrosDescricao(null);
			contaReceberEntity.setDataPagamento(null);
			contaReceberEntity.setValorPago(null);
			
			//contaReceberEntity.setFornecedor(newFornecedorEntity());
			//contaReceberEntity.setNumDocumento(generateRandomString(255));
			contaReceberEntity.setNumDocConcBancaria(null);
			contaReceberEntity.setHistConcBancaria(null);
			contaReceberEntity.setObservacoes(null);
			contaReceberEntity.setAgrupador("teste");
			
			contaReceberEntity = em.persistAndFlush(contaReceberEntity);
			contas.add(contaReceberEntity);
			dataVencimento = dataVencimento.plusMonths(1);
		}
		
		return contas;
	}
	
	
	
	protected ContaReceberLookupResult newContaReceberLookupResult(ContaReceberEntity contaReceberEntity) {
		ContaReceberLookupResult contaReceber = new ContaReceberLookupResult();
		
		contaReceber.setId(contaReceberEntity.getId());
		contaReceber.setDescricao(contaReceberEntity.getDescricao());
		
		return contaReceber;
	}
	
	protected PlanoContaEntity newPlanoContaEntity() {
		PlanoContaEntity planoContaEntity = new PlanoContaEntity();
		
		planoContaEntity.setId(java.util.UUID.randomUUID());
		planoContaEntity.setCodigo(generateRandomString(255));
		planoContaEntity.setDescricao(generateRandomString(255));
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.RECEITA);
		planoContaEntity.setTipoReceitaDespesa(TipoReceitaDespesa.VARIAVEL);
		planoContaEntity.setPlanoContaPai(null);
		planoContaEntity.setAtivo(true);
		planoContaEntity.setDeleted(false);
		
		planoContaEntity = em.persistAndFlush(planoContaEntity);
		
		return planoContaEntity;
	}
	
	protected PlanoContaLookupResult newPlanoContaLookupResult(PlanoContaEntity planoContaEntity) {
		PlanoContaLookupResult planoConta = new PlanoContaLookupResult();
		
		planoConta.setId(planoContaEntity.getId());
		planoConta.setCodigo(planoContaEntity.getCodigo());
		planoConta.setDescricao(planoContaEntity.getDescricao());
		
		return planoConta;
	}
	
	ContaBancariaEntity contaBancariaEntity;
	protected ContaBancariaEntity newContaBancariaConciliacao() {
		if (contaBancariaEntity == null) {
			contaBancariaEntity = new ContaBancariaEntity();
			
			contaBancariaEntity.setId(java.util.UUID.randomUUID());
			contaBancariaEntity.setNomeTitular("Kerubin");
			contaBancariaEntity.setAgencia(newAgenciaBancariaConciliacao());
			contaBancariaEntity.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
			contaBancariaEntity.setNumeroConta(CONTA_BANCARIA_ID);
			contaBancariaEntity.setDigito("01");
			contaBancariaEntity.setDataValidade(getNextDate());
			contaBancariaEntity.setAtivo(true);
			contaBancariaEntity.setDeleted(false);
			
			contaBancariaEntity = em.persistAndFlush(contaBancariaEntity);
		}
		
		return contaBancariaEntity;
	}
	
	protected ContaBancariaLookupResult newContaBancariaLookupResult(ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaLookupResult contaBancaria = new ContaBancariaLookupResult();
		
		contaBancaria.setId(contaBancariaEntity.getId());
		contaBancaria.setNomeTitular(contaBancariaEntity.getNomeTitular());
		contaBancaria.setNumeroConta(contaBancariaEntity.getNumeroConta());
		
		return contaBancaria;
	}
	
	AgenciaBancariaEntity agenciaBancariaEntity;
	protected AgenciaBancariaEntity newAgenciaBancariaConciliacao() {
		if (agenciaBancariaEntity == null) {
			agenciaBancariaEntity = new AgenciaBancariaEntity();
			
			agenciaBancariaEntity.setId(java.util.UUID.randomUUID());
			agenciaBancariaEntity.setBanco(newBancoBradesco());
			agenciaBancariaEntity.setNumeroAgencia(AGENDIA_ID);
			agenciaBancariaEntity.setDigitoAgencia("12");
			agenciaBancariaEntity.setEndereco("Blumenau");
			agenciaBancariaEntity.setDeleted(false);
			
			agenciaBancariaEntity = em.persistAndFlush(agenciaBancariaEntity);
			
		}
		
		return agenciaBancariaEntity;
	}
	
	protected AgenciaBancariaLookupResult newAgenciaBancariaLookupResult(AgenciaBancariaEntity agenciaBancariaEntity) {
		AgenciaBancariaLookupResult agenciaBancaria = new AgenciaBancariaLookupResult();
		
		agenciaBancaria.setId(agenciaBancariaEntity.getId());
		agenciaBancaria.setNumeroAgencia(agenciaBancariaEntity.getNumeroAgencia());
		agenciaBancaria.setDigitoAgencia(agenciaBancariaEntity.getDigitoAgencia());
		agenciaBancaria.setEndereco(agenciaBancariaEntity.getEndereco());
		
		return agenciaBancaria;
	}
	
	BancoEntity bancoBradesco;
	protected BancoEntity newBancoBradesco() {
		if (bancoBradesco == null) {
			bancoBradesco = new BancoEntity();
			bancoBradesco.setId(java.util.UUID.randomUUID());
			bancoBradesco.setNumero(BANCO_ID);
			bancoBradesco.setNome("Banco Bradesco");
			bancoBradesco.setDeleted(false);
			
			bancoBradesco = em.persistAndFlush(bancoBradesco);
		}
		
		return bancoBradesco;
	}
	
	protected BancoLookupResult newBancoLookupResult(BancoEntity bancoEntity) {
		BancoLookupResult banco = new BancoLookupResult();
		
		banco.setId(bancoEntity.getId());
		banco.setNumero(bancoEntity.getNumero());
		banco.setNome(bancoEntity.getNome());
		
		return banco;
	}
	
	
	protected CartaoCreditoLookupResult newCartaoCreditoLookupResult(CartaoCreditoEntity cartaoCreditoEntity) {
		CartaoCreditoLookupResult cartaoCredito = new CartaoCreditoLookupResult();
		
		cartaoCredito.setId(cartaoCreditoEntity.getId());
		cartaoCredito.setNomeTitular(cartaoCreditoEntity.getNomeTitular());
		cartaoCredito.setNumeroCartao(cartaoCreditoEntity.getNumeroCartao());
		
		return cartaoCredito;
	}
	
	protected BandeiraCartaoEntity newBandeiraCartaoEntity() {
		BandeiraCartaoEntity bandeiraCartaoEntity = new BandeiraCartaoEntity();
		
		bandeiraCartaoEntity.setId(java.util.UUID.randomUUID());
		bandeiraCartaoEntity.setNomeBandeira(generateRandomString(255));
		bandeiraCartaoEntity.setDeleted(false);
		
		bandeiraCartaoEntity = em.persistAndFlush(bandeiraCartaoEntity);
		
		return bandeiraCartaoEntity;
	}
	
	protected BandeiraCartaoLookupResult newBandeiraCartaoLookupResult(BandeiraCartaoEntity bandeiraCartaoEntity) {
		BandeiraCartaoLookupResult bandeiraCartao = new BandeiraCartaoLookupResult();
		
		bandeiraCartao.setId(bandeiraCartaoEntity.getId());
		bandeiraCartao.setNomeBandeira(bandeiraCartaoEntity.getNomeBandeira());
		
		return bandeiraCartao;
	}
	
	
	// END TESTS DEPENDENCIES

}

/**********************************************************************************************
Code generated with MKL Plug-in version: 27.0.12
Code generated at time stamp: 2019-11-06T06:23:00.711
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberDTOConverter;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberListFilterPredicate;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberService;
import br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceberServiceImpl;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;


@RunWith(SpringRunner.class)
public class ConciliacaoBancariaServiceTest extends FinanceiroContasReceberBaseEntityTest {
	
	private static final String BANCO_ID = "237";
	private static final String AGENDIA_ID = "12345";
	private static final String CONTA_BANCARIA_ID = "98765";
	
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
		
	}
	
	
	@Inject
	protected ContaReceberService contaReceberService;
	
	@Inject
	protected ContaReceberDTOConverter contaReceberDTOConverter;
	
	@Inject
	protected ContaReceberRepository contaReceberRepository;
	
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
	public void testAplicarConciliacaoBancariaComSucesso() {
		
		ContaReceberEntity contaReceber1 = newContaReceber1();
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(100.55))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(contaReceber1.getId())
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaReceber1.getId(), contaReceber1.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(t1.getTrnData(), t1.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t1.getTrnDocumento(), t1.getTrnHistorico()),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
		
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroSemTituloConciliadoId() {
		
		ContaReceberEntity contaReceber1 = newContaReceber1();
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		UUID unknownId = UUID.randomUUID();
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(100.55))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(unknownId)
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		String erroMsg = "Título não localizado com o id: " + unknownId.toString();
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(unknownId,           null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
		
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroSemValor() {
		ContaReceberEntity contaReceber1 = newContaReceber1();
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = "Valor da transação está vazio para baixar conta via conciliação";
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(null)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(contaReceber1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaReceber1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroSemData() {
		ContaReceberEntity contaReceber1 = newContaReceber1();
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = "Data da transação está vazia para baixar conta via conciliação";
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(contaReceber1.getValor())
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(null)
				.tituloConciliadoId(contaReceber1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaReceber1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroTipoNaoCredito() {
		ContaReceberEntity contaReceber1 = newContaReceber1();
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = "Tipo da transação deveria ser CRÉDITO mas é: " + TipoTransacao.DEBITO;
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(contaReceber1.getValor())
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now())
				.tituloConciliadoId(contaReceber1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaReceber1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroContaJaBaixada() {
		ContaReceberEntity contaReceber1 = newContaReceber1();
		contaReceber1.setDataPagamento(LocalDate.now());
		contaReceber1.setNumDocConcBancaria("012345");
		
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = format("Conta já baixada em: {0}, doc: {1}", contaReceber1.getDataPagamento(), contaReceber1.getNumDocConcBancaria());
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(contaReceber1.getValor())
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now())
				.tituloConciliadoId(contaReceber1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaReceber1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(LocalDate.now(), null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), "012345",             null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroContaValorDivergente() {
		ContaReceberEntity contaReceber1 = newContaReceber1();
		contaReceber1.setValor(BigDecimal.valueOf(181.18));
		
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(181.00))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now())
				.tituloConciliadoId(contaReceber1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		String erroMsg = format("Valor da conta: {0}, diverge do valor da transação de conciliação: {1}", contaReceber1.getValor(), t1.getTrnValor());
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaReceber1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroBancoNaoEncontrado() {
		
		ContaReceberEntity contaReceber1 = newContaReceber1();
		ContaReceberEntity contaReceber2 = newContaReceber2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		
		String bancoId = BANCO_ID + "-xxx";
		conciliacaoBancariaDTO.setBancoId(bancoId);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = MessageFormat.format("Conta bancária não encontrada para bancoId:{0}, agenciaId:{1}, contaBancariaId:{2}", bancoId, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		UUID unknownId = UUID.randomUUID();
		String tituloConciliadoDesc = contaReceber1.getDescricao() + " xxx";
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(100.55))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(unknownId)
				.tituloConciliadoDesc(tituloConciliadoDesc)
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaReceber2.getId())
				.tituloConciliadoDesc(contaReceber2.getDescricao())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(unknownId,           tituloConciliadoDesc,       /*getConciliadoComErro=*/true, /*getConciliadoMsg=*/erroMsg, null, SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER),
				tuple(contaReceber2.getId(), contaReceber2.getDescricao(), /*getConciliadoComErro=*/true, /*getConciliadoMsg=*/erroMsg, null, SituacaoConciliacaoTrn.CONCILIAR_CONTAS_RECEBER)
				);
		
		
		List<ContaReceberEntity> contas = Arrays.asList(contaReceber1, contaReceber2);
		assertThat(contas).hasSize(2)
		.extracting(ContaReceberEntity::getDataPagamento,
				ContaReceberEntity::getValorPago,
				ContaReceberEntity::getFormaPagamento,
				ContaReceberEntity::getContaBancaria,
				ContaReceberEntity::getNumDocConcBancaria,
				ContaReceberEntity::getHistConcBancaria
				)
		.contains(
				tuple(null, null, FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null, null),
				tuple(null, null, FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null, null)
				);
		
	}
	
	// BEGIN TESTS DEPENDENCIES
	
	protected ContaReceberEntity newContaReceber1() {
		ContaReceberEntity contaReceberEntity = new ContaReceberEntity();
		
		contaReceberEntity.setPlanoContas(newPlanoContaEntity());
		contaReceberEntity.setDescricao("Conta Receber 1");
		contaReceberEntity.setDataVencimento(LocalDate.now());
		contaReceberEntity.setValor(new java.math.BigDecimal("100.55"));
		contaReceberEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
		
		contaReceberEntity.setContaBancaria(newContaBancariaConciliacao());
		
		contaReceberEntity.setCartaoCredito(null);
		contaReceberEntity.setOutrosDescricao(generateRandomString(255));
		contaReceberEntity.setDataPagamento(null);
		
		contaReceberEntity.setCliente(null);
		contaReceberEntity.setNumDocumento(generateRandomString(255));
		contaReceberEntity.setNumDocConcBancaria(null);
		contaReceberEntity.setHistConcBancaria(null);
		contaReceberEntity.setObservacoes(null);
		contaReceberEntity.setAgrupador("teste");
		
		contaReceberEntity = em.persistAndFlush(contaReceberEntity);
		
		return contaReceberEntity;
	}
	
	protected ContaReceberEntity newContaReceber2() {
		ContaReceberEntity contaReceberEntity = new ContaReceberEntity();
		
		contaReceberEntity.setPlanoContas(newPlanoContaEntity());
		contaReceberEntity.setDescricao("Conta Receber 2");
		contaReceberEntity.setDataVencimento(LocalDate.now());
		contaReceberEntity.setValor(new java.math.BigDecimal("200.65"));
		contaReceberEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
		
		contaReceberEntity.setContaBancaria(newContaBancariaConciliacao());
		
		contaReceberEntity.setCartaoCredito(null);
		contaReceberEntity.setOutrosDescricao(generateRandomString(255));
		contaReceberEntity.setDataPagamento(null);
		
		contaReceberEntity.setCliente(null);
		contaReceberEntity.setNumDocumento(generateRandomString(255));
		contaReceberEntity.setNumDocConcBancaria(null);
		contaReceberEntity.setHistConcBancaria(null);
		contaReceberEntity.setObservacoes(null);
		contaReceberEntity.setAgrupador("teste");
		
		contaReceberEntity = em.persistAndFlush(contaReceberEntity);
		
		return contaReceberEntity;
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
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
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

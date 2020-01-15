/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:19.082
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.FormaPagamento;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.financeiro.contasreceber.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.contasreceber.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.TipoContaBancaria;
import br.com.kerubin.api.financeiro.contasreceber.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.contasreceber.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.contasreceber.TipoPessoa;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import br.com.kerubin.api.financeiro.contasreceber.common.PageResult;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.data.domain.Sort;
import java.util.Collection;
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteAutoComplete;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.contasreceber.FinanceiroContasReceberBaseEntityTest;


@RunWith(SpringRunner.class)
public class ContaReceberServiceTest extends FinanceiroContasReceberBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
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
	
	@Inject
	protected ClienteRepository clienteRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		ContaReceber contaReceber = new ContaReceber();
		
		contaReceber.setId(java.util.UUID.randomUUID());
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaReceber.setPlanoContas(planoContas);
		
		contaReceber.setDescricao(generateRandomString(255));
		contaReceber.setDataVencimento(getNextDate());
		contaReceber.setValor(new java.math.BigDecimal("2000.27690"));
		contaReceber.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		contaReceber.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		contaReceber.setCartaoCredito(cartaoCredito);
		
		contaReceber.setOutrosDescricao(generateRandomString(255));
		contaReceber.setDataPagamento(getNextDate());
		contaReceber.setValorDesconto(new java.math.BigDecimal("31680.6563"));
		contaReceber.setValorMulta(new java.math.BigDecimal("32619.3599"));
		contaReceber.setValorJuros(new java.math.BigDecimal("22688.4155"));
		contaReceber.setValorAcrescimos(new java.math.BigDecimal("8036.25288"));
		contaReceber.setValorPago(new java.math.BigDecimal("12168.4558"));
		
		ClienteEntity clienteEntityParam = newClienteEntity();
		ClienteLookupResult cliente = newClienteLookupResult(clienteEntityParam);
		contaReceber.setCliente(cliente);
		
		contaReceber.setNumDocumento(generateRandomString(255));
		contaReceber.setIdConcBancaria(generateRandomString(255));
		contaReceber.setHistConcBancaria(generateRandomString(255));
		contaReceber.setNumDocConcBancaria(generateRandomString(255));
		contaReceber.setObservacoes(generateRandomString(1000));
		contaReceber.setAgrupador(generateRandomString(255));
		ContaReceberEntity contaReceberEntity = contaReceberService.create(contaReceberDTOConverter.convertDtoToEntity(contaReceber));
		em.flush();
		verify(publisher, times(0)).publish(any());
		ContaReceber actual = contaReceberDTOConverter.convertEntityToDto(contaReceberEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaReceber, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaReceber.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(contaReceber.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(contaReceber.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCliente().getId()).isNotNull();
		assertThat(actual.getCliente()).isEqualToIgnoringGivenFields(contaReceber.getCliente(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		ContaReceber contaReceber = new ContaReceber();
		
		contaReceber.setId(java.util.UUID.randomUUID());
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaReceber.setPlanoContas(planoContas);
		
		contaReceber.setDescricao(generateRandomString(255));
		contaReceber.setDataVencimento(getNextDate());
		contaReceber.setValor(new java.math.BigDecimal("27039.27694"));
		contaReceber.setFormaPagamento(FormaPagamento.DINHEIRO);
		ContaReceberEntity contaReceberEntity = contaReceberService.create(contaReceberDTOConverter.convertDtoToEntity(contaReceber));
		em.flush();
		verify(publisher, times(0)).publish(any());
		ContaReceber actual = contaReceberDTOConverter.convertEntityToDto(contaReceberEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaReceber, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaReceber.getPlanoContas(), IGNORED_FIELDS);
		
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		assertThat(actual.getCliente()).isNull();
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		ContaReceberEntity expectedContaReceberEntity = newContaReceberEntity();
		java.util.UUID id = expectedContaReceberEntity.getId();
		ContaReceber expected = contaReceberDTOConverter.convertEntityToDto(expectedContaReceberEntity);
		ContaReceberEntity readContaReceberEntity = contaReceberService.read(id);
		ContaReceber actual = contaReceberDTOConverter.convertEntityToDto(readContaReceberEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		ContaReceberEntity oldContaReceberEntity = newContaReceberEntity();
		java.util.UUID id = oldContaReceberEntity.getId();
				
		ContaReceber contaReceber = new ContaReceber();
		contaReceber.setId(id);
		
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaReceber.setPlanoContas(planoContas);
		
		contaReceber.setDescricao(generateRandomString(255));
		contaReceber.setDataVencimento(getNextDate());
		contaReceber.setValor(new java.math.BigDecimal("24277.11837"));
		contaReceber.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		contaReceber.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		contaReceber.setCartaoCredito(cartaoCredito);
		
		contaReceber.setOutrosDescricao(generateRandomString(255));
		contaReceber.setDataPagamento(getNextDate());
		contaReceber.setValorDesconto(new java.math.BigDecimal("25476.12150"));
		contaReceber.setValorMulta(new java.math.BigDecimal("24521.3121"));
		contaReceber.setValorJuros(new java.math.BigDecimal("20774.25767"));
		contaReceber.setValorAcrescimos(new java.math.BigDecimal("28711.18293"));
		contaReceber.setValorPago(new java.math.BigDecimal("29345.7308"));
		
		ClienteEntity clienteEntityParam = newClienteEntity();
		ClienteLookupResult cliente = newClienteLookupResult(clienteEntityParam);
		contaReceber.setCliente(cliente);
		
		contaReceber.setNumDocumento(generateRandomString(255));
		contaReceber.setIdConcBancaria(generateRandomString(255));
		contaReceber.setHistConcBancaria(generateRandomString(255));
		contaReceber.setNumDocConcBancaria(generateRandomString(255));
		contaReceber.setObservacoes(generateRandomString(1000));
		contaReceber.setAgrupador(generateRandomString(255));
		ContaReceberEntity contaReceberEntity = contaReceberService.update(id, contaReceberDTOConverter.convertDtoToEntity(contaReceber));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		ContaReceber actual = contaReceberDTOConverter.convertEntityToDto(contaReceberEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaReceber, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaReceber.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(contaReceber.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(contaReceber.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCliente().getId()).isNotNull();
		assertThat(actual.getCliente()).isEqualToIgnoringGivenFields(contaReceber.getCliente(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		ContaReceberEntity oldContaReceberEntity = newContaReceberEntity();
		java.util.UUID id = oldContaReceberEntity.getId();
				
		ContaReceber contaReceber = new ContaReceber();
		contaReceber.setId(id);
		
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaReceber.setPlanoContas(planoContas);
		
		contaReceber.setDescricao(generateRandomString(255));
		contaReceber.setDataVencimento(getNextDate());
		contaReceber.setValor(new java.math.BigDecimal("17828.28113"));
		contaReceber.setFormaPagamento(FormaPagamento.DINHEIRO);
		ContaReceberEntity contaReceberEntity = contaReceberService.update(id, contaReceberDTOConverter.convertDtoToEntity(contaReceber));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		ContaReceber actual = contaReceberDTOConverter.convertEntityToDto(contaReceberEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaReceber, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaReceber.getPlanoContas(), IGNORED_FIELDS);
		
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		assertThat(actual.getCliente()).isNull();
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		ContaReceberEntity expected = newContaReceberEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(ContaReceberEntity.class, id);
		assertThat(expected).isNotNull();
		contaReceberService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(ContaReceberEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByDescricao() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 33 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaReceber.
		ContaReceberListFilter listFilter = new ContaReceberListFilter();
		
		// Extracts 7 records of ContaReceberEntity randomly from testData.
		final int resultSize = 7;
		List<ContaReceberEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaReceberEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaReceberEntity::getDescricao).collect(Collectors.toList());
		listFilter.setDescricao(descricaoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<ContaReceberEntity> page = contaReceberService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<ContaReceber> content = page.getContent().stream().map(it -> contaReceberDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<ContaReceber> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with descricaoListFilter elements based on descricao field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(ContaReceber::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByDescricaoWithoutResults() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 33 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaReceber.
		ContaReceberListFilter listFilter = new ContaReceberListFilter();
		
		// Generates a list with only ContaReceberEntity.descricao field with 1 not found data in the database and configure this list as a filter.
		List<String> descricaoListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setDescricao(descricaoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<ContaReceberEntity> page = contaReceberService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<ContaReceber> content = page.getContent().stream().map(it -> contaReceberDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<ContaReceber> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown descricao field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	
	@Test
	public void testList_SortingByDataVencimento() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 10 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 10;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 10 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaReceber.
		ContaReceberListFilter listFilter = new ContaReceberListFilter();
		
		// Generates a pageable configuration, with sorting.
		Sort sort = Sort.by("dataVencimento").ascending(); // select ... order by dataVencimento ascending
		int pageIndex = 0; // First page starts at index zero.
		int size = 10; // Max of 10 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size, sort);
		
		// Call service list method.
		Page<ContaReceberEntity> page = contaReceberService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<ContaReceber> content = page.getContent().stream().map(it -> contaReceberDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<ContaReceber> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Extracts a list with only ContaReceberEntity.dataVencimento fields.
		List<java.time.LocalDate> dataVencimentoTestDataList = testData.stream().map(ContaReceberEntity::getDataVencimento).collect(Collectors.toList());
		
		// Sort dataVencimento in ascending order.
		Collections.sort(dataVencimentoTestDataList);
	
		// Asserts that result has size 10 in a specific order.
		assertThat(pageResult.getContent())
		.hasSize(10)
		.extracting(ContaReceber::getDataVencimento)
		.containsExactlyElementsOf(dataVencimentoTestDataList);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(10);
		assertThat(pageResult.getTotalElements()).isEqualTo(10);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 33 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaReceberEntity randomly from testData.
		final int resultSize = 1;
		List<ContaReceberEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaReceberEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaReceberEntity::getDescricao).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<ContaReceberAutoComplete> result = contaReceberService.autoComplete(query);
		
		// Assert ContaReceberAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaReceberAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testContaReceberDescricaoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 33 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaReceberEntity randomly from testData.
		final int resultSize = 1;
		List<ContaReceberEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaReceberEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaReceberEntity::getDescricao).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<ContaReceberDescricaoAutoComplete> result = contaReceberService.contaReceberDescricaoAutoComplete(query);
		// Assert ContaReceberDescricaoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaReceberDescricaoAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	
	@Test
	public void testContaReceberHistConcBancariaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 33 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaReceberEntity randomly from testData.
		final int resultSize = 1;
		List<ContaReceberEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaReceberEntity.histConcBancaria field and configure this list as a filter.
		List<String> histConcBancariaListFilter = filterTestData.stream().map(ContaReceberEntity::getHistConcBancaria).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = histConcBancariaListFilter.get(0);
		Collection<ContaReceberHistConcBancariaAutoComplete> result = contaReceberService.contaReceberHistConcBancariaAutoComplete(query);
		// Assert ContaReceberHistConcBancariaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaReceberHistConcBancariaAutoComplete::getHistConcBancaria)
		.containsExactlyInAnyOrderElementsOf(histConcBancariaListFilter);
	}
	
	
	@Test
	public void testContaReceberAgrupadorAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 33 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaReceberEntity randomly from testData.
		final int resultSize = 1;
		List<ContaReceberEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaReceberEntity.agrupador field and configure this list as a filter.
		List<String> agrupadorListFilter = filterTestData.stream().map(ContaReceberEntity::getAgrupador).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = agrupadorListFilter.get(0);
		Collection<ContaReceberAgrupadorAutoComplete> result = contaReceberService.contaReceberAgrupadorAutoComplete(query);
		// Assert ContaReceberAgrupadorAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaReceberAgrupadorAutoComplete::getAgrupador)
		.containsExactlyInAnyOrderElementsOf(agrupadorListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testContaReceberPlanoContasAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		String query = codigoListFilter.get(0);
		
		Collection<PlanoContaAutoComplete> result = contaReceberService.planoContaPlanoContasAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	
	@Test
	public void testContaReceberContaBancariaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaBancariaEntity for this test.
		List<ContaBancariaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaBancariaEntity());
		}
		
		// Check if 33 records of ContaBancariaEntity was generated.
		long count = contaBancariaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaBancariaEntity randomly from testData.
		final int resultSize = 1;
		List<ContaBancariaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaBancariaEntity.nomeTitular field and configure this list as a filter.
		List<String> nomeTitularListFilter = filterTestData.stream().map(ContaBancariaEntity::getNomeTitular).collect(Collectors.toList());
		String query = nomeTitularListFilter.get(0);
		
		Collection<ContaBancariaAutoComplete> result = contaReceberService.contaBancariaContaBancariaAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaBancariaAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testContaReceberCartaoCreditoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CartaoCreditoEntity for this test.
		List<CartaoCreditoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCartaoCreditoEntity());
		}
		
		// Check if 33 records of CartaoCreditoEntity was generated.
		long count = cartaoCreditoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CartaoCreditoEntity randomly from testData.
		final int resultSize = 1;
		List<CartaoCreditoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CartaoCreditoEntity.nomeTitular field and configure this list as a filter.
		List<String> nomeTitularListFilter = filterTestData.stream().map(CartaoCreditoEntity::getNomeTitular).collect(Collectors.toList());
		String query = nomeTitularListFilter.get(0);
		
		Collection<CartaoCreditoAutoComplete> result = contaReceberService.cartaoCreditoCartaoCreditoAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CartaoCreditoAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testContaReceberClienteAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ClienteEntity for this test.
		List<ClienteEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newClienteEntity());
		}
		
		// Check if 33 records of ClienteEntity was generated.
		long count = clienteRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ClienteEntity randomly from testData.
		final int resultSize = 1;
		List<ClienteEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ClienteEntity.nome field and configure this list as a filter.
		List<String> nomeListFilter = filterTestData.stream().map(ClienteEntity::getNome).collect(Collectors.toList());
		String query = nomeListFilter.get(0);
		
		Collection<ClienteAutoComplete> result = contaReceberService.clienteClienteAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ClienteAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testGetContaReceberSumFields() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 2 records of data for ContaReceberEntity for this test.
		List<ContaReceberEntity> testData = new ArrayList<>();
		final int lastRecord = 2;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaReceberEntity());
		}
		
		// Check if 2 records of ContaReceberEntity was generated.
		long count = contaReceberRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaReceber.
		ContaReceberListFilter listFilter = new ContaReceberListFilter();
		
		// Extracts 2 records of ContaReceberEntity randomly from testData.
		final int resultSize = 2;
		List<ContaReceberEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		ContaReceberSumFields expected = new ContaReceberSumFields();
		
		BigDecimal sumValor = filterTestData.stream().map(it -> it.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValor(sumValor);
		
		BigDecimal sumValorDesconto = filterTestData.stream().map(it -> it.getValorDesconto()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorDesconto(sumValorDesconto);
		
		BigDecimal sumValorMulta = filterTestData.stream().map(it -> it.getValorMulta()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorMulta(sumValorMulta);
		
		BigDecimal sumValorJuros = filterTestData.stream().map(it -> it.getValorJuros()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorJuros(sumValorJuros);
		
		BigDecimal sumValorAcrescimos = filterTestData.stream().map(it -> it.getValorAcrescimos()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorAcrescimos(sumValorAcrescimos);
		
		BigDecimal sumValorPago = filterTestData.stream().map(it -> it.getValorPago()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorPago(sumValorPago);
		ContaReceberSumFields actual = contaReceberService.getContaReceberSumFields(listFilter);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testActionFazerCopiasContaReceber_1Copy() {
		ContaReceberEntity baseEntity = newContaReceberEntity();
		
		ContaReceberMakeCopies contaReceberMakeCopies = new ContaReceberMakeCopies();
		contaReceberMakeCopies.setId(baseEntity.getId());
		contaReceberMakeCopies.setAgrupador(baseEntity.getAgrupador());
		contaReceberMakeCopies.setNumberOfCopies(1L);
		contaReceberMakeCopies.setReferenceFieldInterval(30L);
		contaReceberService.actionFazerCopiasContaReceber(contaReceberMakeCopies);
		
		// Mount expected
		LocalDate lastDate = baseEntity.getDataVencimento();
		List<ContaReceberEntity> copies = new ArrayList<>(2);
		long interval = contaReceberMakeCopies.getReferenceFieldInterval();
		int fixedDay = lastDate.getDayOfMonth();
		int fixedDayCopy = fixedDay;
		for (int i = 0; i < contaReceberMakeCopies.getNumberOfCopies(); i++) {
			ContaReceberEntity copiedEntity = baseEntity.clone();
			copies.add(copiedEntity);
			copiedEntity.setId(null);
			lastDate = lastDate.plus(interval, ChronoUnit.DAYS);
			if (interval == 30) {
				int length = lastDate.lengthOfMonth();
				while (fixedDay > length) {
				    fixedDay--;
				}
				lastDate = lastDate.withDayOfMonth(fixedDay);
				fixedDay = fixedDayCopy;
			}
			copiedEntity.setDataVencimento(lastDate);
		}
		
		copies.add(baseEntity);
		
		List<ContaReceberEntity> actual = contaReceberRepository.findAll();
		
		actual.sort(Comparator.comparing(ContaReceberEntity::getDataVencimento));
		copies.sort(Comparator.comparing(ContaReceberEntity::getDataVencimento));
		
		assertThat(actual).hasSize(2);
		assertThat(copies).hasSize(2);
		
		for (int i = 0; i < actual.size(); i++) {
			assertThat(actual.get(i)).isEqualToIgnoringGivenFields(copies.get(i), IGNORED_FIELDS);
		}
		
	}
	
	@Test
	public void testActionFazerCopiasContaReceber_11Copies() {
		ContaReceberEntity baseEntity = newContaReceberEntity();
					
		ContaReceberMakeCopies contaReceberMakeCopies = new ContaReceberMakeCopies();
		contaReceberMakeCopies.setId(baseEntity.getId());
		contaReceberMakeCopies.setAgrupador(baseEntity.getAgrupador());
		contaReceberMakeCopies.setNumberOfCopies(11L);
		contaReceberMakeCopies.setReferenceFieldInterval(30L);
		
		contaReceberService.actionFazerCopiasContaReceber(contaReceberMakeCopies);
		
		// Mount expected
		LocalDate lastDate = baseEntity.getDataVencimento();
		List<ContaReceberEntity> copies = new ArrayList<>(12);
		long interval = contaReceberMakeCopies.getReferenceFieldInterval();
		int fixedDay = lastDate.getDayOfMonth();
		int fixedDayCopy = fixedDay;
		for (int i = 0; i < contaReceberMakeCopies.getNumberOfCopies(); i++) {
			ContaReceberEntity copiedEntity = baseEntity.clone();
			copies.add(copiedEntity);
			copiedEntity.setId(null);
			lastDate = lastDate.plus(interval, ChronoUnit.DAYS);
			if (interval == 30) {
				int length = lastDate.lengthOfMonth();
				while (fixedDay > length) {
				    fixedDay--;
				}
				lastDate = lastDate.withDayOfMonth(fixedDay);
				fixedDay = fixedDayCopy;
			}
			copiedEntity.setDataVencimento(lastDate);
		}
		
		copies.add(baseEntity);
		
		List<ContaReceberEntity> actual = contaReceberRepository.findAll();
		
		actual.sort(Comparator.comparing(ContaReceberEntity::getDataVencimento));
		copies.sort(Comparator.comparing(ContaReceberEntity::getDataVencimento));
		
		assertThat(actual).hasSize(12);
		assertThat(copies).hasSize(12);
		
		for (int i = 0; i < actual.size(); i++) {
			assertThat(actual.get(i)).isEqualToIgnoringGivenFields(copies.get(i), IGNORED_FIELDS);
		}
	}
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected ContaReceberEntity newContaReceberEntity() {
		ContaReceberEntity contaReceberEntity = new ContaReceberEntity();
		
		contaReceberEntity.setPlanoContas(newPlanoContaEntity());
		contaReceberEntity.setDescricao(generateRandomString(255));
		contaReceberEntity.setDataVencimento(getNextDate());
		contaReceberEntity.setValor(new java.math.BigDecimal("21788.22321"));
		contaReceberEntity.setFormaPagamento(FormaPagamento.DINHEIRO);
		contaReceberEntity.setContaBancaria(newContaBancariaEntity());
		contaReceberEntity.setCartaoCredito(newCartaoCreditoEntity());
		contaReceberEntity.setOutrosDescricao(generateRandomString(255));
		contaReceberEntity.setDataPagamento(getNextDate());
		contaReceberEntity.setValorDesconto(new java.math.BigDecimal("16754.24507"));
		contaReceberEntity.setValorMulta(new java.math.BigDecimal("26730.13490"));
		contaReceberEntity.setValorJuros(new java.math.BigDecimal("19999.29316"));
		contaReceberEntity.setValorAcrescimos(new java.math.BigDecimal("7886.31341"));
		contaReceberEntity.setValorPago(new java.math.BigDecimal("10695.9913"));
		contaReceberEntity.setCliente(newClienteEntity());
		contaReceberEntity.setNumDocumento(generateRandomString(255));
		contaReceberEntity.setIdConcBancaria(generateRandomString(255));
		contaReceberEntity.setHistConcBancaria(generateRandomString(255));
		contaReceberEntity.setNumDocConcBancaria(generateRandomString(255));
		contaReceberEntity.setObservacoes(generateRandomString(1000));
		contaReceberEntity.setAgrupador(generateRandomString(255));
		
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
	
	
	protected ContaBancariaEntity newContaBancariaEntity() {
		ContaBancariaEntity contaBancariaEntity = new ContaBancariaEntity();
		
		contaBancariaEntity.setId(java.util.UUID.randomUUID());
		contaBancariaEntity.setNomeTitular(generateRandomString(255));
		contaBancariaEntity.setAgencia(newAgenciaBancariaEntity());
		contaBancariaEntity.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancariaEntity.setNumeroConta(generateRandomString(30));
		contaBancariaEntity.setDigito(generateRandomString(10));
		contaBancariaEntity.setDataValidade(getNextDate());
		contaBancariaEntity.setAtivo(true);
		contaBancariaEntity.setDeleted(false);
		
		contaBancariaEntity = em.persistAndFlush(contaBancariaEntity);
		return contaBancariaEntity;
	}
	
	
	protected ContaBancariaLookupResult newContaBancariaLookupResult(ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaLookupResult contaBancaria = new ContaBancariaLookupResult();
		
		contaBancaria.setId(contaBancariaEntity.getId());
		contaBancaria.setNomeTitular(contaBancariaEntity.getNomeTitular());
		contaBancaria.setNumeroConta(contaBancariaEntity.getNumeroConta());
		
		return contaBancaria;
	}
	
	
	protected AgenciaBancariaEntity newAgenciaBancariaEntity() {
		AgenciaBancariaEntity agenciaBancariaEntity = new AgenciaBancariaEntity();
		
		agenciaBancariaEntity.setId(java.util.UUID.randomUUID());
		agenciaBancariaEntity.setBanco(newBancoEntity());
		agenciaBancariaEntity.setNumeroAgencia(generateRandomString(50));
		agenciaBancariaEntity.setDigitoAgencia(generateRandomString(10));
		agenciaBancariaEntity.setEndereco(generateRandomString(255));
		agenciaBancariaEntity.setDeleted(false);
		
		agenciaBancariaEntity = em.persistAndFlush(agenciaBancariaEntity);
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
	
	
	protected BancoEntity newBancoEntity() {
		BancoEntity bancoEntity = new BancoEntity();
		
		bancoEntity.setId(java.util.UUID.randomUUID());
		bancoEntity.setNumero(generateRandomString(20));
		bancoEntity.setNome(generateRandomString(255));
		bancoEntity.setDeleted(false);
		
		bancoEntity = em.persistAndFlush(bancoEntity);
		return bancoEntity;
	}
	
	
	protected BancoLookupResult newBancoLookupResult(BancoEntity bancoEntity) {
		BancoLookupResult banco = new BancoLookupResult();
		
		banco.setId(bancoEntity.getId());
		banco.setNumero(bancoEntity.getNumero());
		banco.setNome(bancoEntity.getNome());
		
		return banco;
	}
	
	
	protected CartaoCreditoEntity newCartaoCreditoEntity() {
		CartaoCreditoEntity cartaoCreditoEntity = new CartaoCreditoEntity();
		
		cartaoCreditoEntity.setId(java.util.UUID.randomUUID());
		cartaoCreditoEntity.setBanco(newBancoEntity());
		cartaoCreditoEntity.setNomeTitular(generateRandomString(255));
		cartaoCreditoEntity.setNumeroCartao(generateRandomString(50));
		cartaoCreditoEntity.setValidade(getNextDate());
		cartaoCreditoEntity.setValorLimite(new java.math.BigDecimal("2370.24038"));
		cartaoCreditoEntity.setBandeiraCartao(newBandeiraCartaoEntity());
		cartaoCreditoEntity.setAtivo(true);
		cartaoCreditoEntity.setDeleted(false);
		
		cartaoCreditoEntity = em.persistAndFlush(cartaoCreditoEntity);
		return cartaoCreditoEntity;
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
	
	
	protected ClienteEntity newClienteEntity() {
		ClienteEntity clienteEntity = new ClienteEntity();
		
		clienteEntity.setId(java.util.UUID.randomUUID());
		clienteEntity.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		clienteEntity.setNome(generateRandomString(255));
		clienteEntity.setCnpjCPF(generateRandomString(255));
		clienteEntity.setDeleted(false);
		
		clienteEntity = em.persistAndFlush(clienteEntity);
		return clienteEntity;
	}
	
	
	protected ClienteLookupResult newClienteLookupResult(ClienteEntity clienteEntity) {
		ClienteLookupResult cliente = new ClienteLookupResult();
		
		cliente.setId(clienteEntity.getId());
		cliente.setNome(clienteEntity.getNome());
		
		return cliente;
	}
	// END TESTS DEPENDENCIES

}
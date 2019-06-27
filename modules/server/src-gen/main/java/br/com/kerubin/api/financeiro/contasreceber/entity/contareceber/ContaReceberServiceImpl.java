/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;		

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.Collection;

import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteAutoComplete;

import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
 
@Service
public class ContaReceberServiceImpl implements ContaReceberService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ContaReceberRepository contaReceberRepository;
	
	@Autowired
	private ContaReceberListFilterPredicate contaReceberListFilterPredicate;
	
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Transactional
	@Override
	public ContaReceberEntity create(ContaReceberEntity contaReceberEntity) {
		return contaReceberRepository.save(contaReceberEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaReceberEntity read(java.util.UUID id) {
		return getContaReceberEntity(id);
	}
	
	@Transactional
	@Override
	public ContaReceberEntity update(java.util.UUID id, ContaReceberEntity contaReceberEntity) {
		ContaReceberEntity entity = getContaReceberEntity(id);
		BeanUtils.copyProperties(contaReceberEntity, entity, "id");
		entity = contaReceberRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		contaReceberRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<ContaReceberEntity> list(ContaReceberListFilter contaReceberListFilter, Pageable pageable) {
		Predicate predicate = contaReceberListFilterPredicate.mountAndGetPredicate(contaReceberListFilter);
		
		Page<ContaReceberEntity> resultPage = contaReceberRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected ContaReceberEntity getContaReceberEntity(java.util.UUID id) {
		Optional<ContaReceberEntity> contaReceberEntity = contaReceberRepository.findById(id);
		if (!contaReceberEntity.isPresent()) {
			throw new IllegalArgumentException("ContaReceber not found:" + id.toString());
		}
		return contaReceberEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaReceberAutoComplete> autoComplete(String query) {
		Collection<ContaReceberAutoComplete> result = contaReceberRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query) {
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ClienteAutoComplete> clienteClienteAutoComplete(String query) {
		Collection<ClienteAutoComplete> result = clienteRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaReceberDescricaoAutoComplete> contaReceberDescricaoAutoComplete(String query) {
		Collection<ContaReceberDescricaoAutoComplete> result = contaReceberRepository.contaReceberDescricaoAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaReceberAgrupadorAutoComplete> contaReceberAgrupadorAutoComplete(String query) {
		Collection<ContaReceberAgrupadorAutoComplete> result = contaReceberRepository.contaReceberAgrupadorAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaReceberSumFields getContaReceberSumFields(ContaReceberListFilter contaReceberListFilter) {
		Predicate predicate = contaReceberListFilterPredicate.mountAndGetPredicate(contaReceberListFilter);
		
		QContaReceberEntity qEntity = QContaReceberEntity.contaReceberEntity;
		JPAQueryFactory query = new JPAQueryFactory(em);
		ContaReceberSumFields result = query.select(
				Projections.bean(ContaReceberSumFields.class, 
						qEntity.valor.sum().coalesce(BigDecimal.ZERO).as("sumValor"), 
						qEntity.valorDesconto.sum().coalesce(BigDecimal.ZERO).as("sumValorDesconto"), 
						qEntity.valorMulta.sum().coalesce(BigDecimal.ZERO).as("sumValorMulta"), 
						qEntity.valorJuros.sum().coalesce(BigDecimal.ZERO).as("sumValorJuros"), 
						qEntity.valorAcrescimos.sum().coalesce(BigDecimal.ZERO).as("sumValorAcrescimos"), 
						qEntity.valorPago.sum().coalesce(BigDecimal.ZERO).as("sumValorPago")
				))
		.from(qEntity)
		.where(predicate)
		.fetchOne();
		
		return result;
	}
	
	
	@Transactional
	@Override
	public void actionBaixarContaComUmClique(java.util.UUID id) {
		ContaReceberEntity contaReceber = getContaReceberEntity(id);
		
		if (Objects.isNull(contaReceber.getDataPagamento())) {
			contaReceber.setDataPagamento(LocalDate.now());
			contaReceber.setValorPago(contaReceber.getValor());
			
			contaReceber = contaReceberRepository.save(contaReceber);
		}
		else {
			throw new IllegalStateException("Condição inválida para executar a ação: actionBaixarContaComUmClique.");
		}
		
	}
	
	@Transactional
	@Override
	public void actionEstornarRecebimentoContaComUmClique(java.util.UUID id) {
		ContaReceberEntity contaReceber = getContaReceberEntity(id);
		
		if (Objects.nonNull(contaReceber.getDataPagamento())) {
			contaReceber.setDataPagamento(null);
			contaReceber.setValorPago(null);
			
			contaReceber = contaReceberRepository.save(contaReceber);
		}
		else {
			throw new IllegalStateException("Condição inválida para executar a ação: actionEstornarRecebimentoContaComUmClique.");
		}
		
	}
	
	@Transactional
	@Override
	public void actionFazerCopiasContaReceber(ContaReceberMakeCopies contaReceberMakeCopies) {
		if (StringUtils.isBlank(contaReceberMakeCopies.getAgrupador())) {
			throw new IllegalArgumentException("O campo 'Agrupador' deve ser informado.");
		}
		
		ContaReceberEntity contaReceber = getContaReceberEntity(contaReceberMakeCopies.getId());
		contaReceber.setAgrupador(contaReceberMakeCopies.getAgrupador());
		
		LocalDate lastDate = contaReceber.getDataVencimento();
		List<ContaReceberEntity> copies = new ArrayList<>();
		long interval = contaReceberMakeCopies.getReferenceFieldInterval();
		int fixedDay = lastDate.getDayOfMonth();
		for (int i = 0; i < contaReceberMakeCopies.getNumberOfCopies(); i++) {
			ContaReceberEntity copiedEntity = contaReceber.clone();
			copies.add(copiedEntity);
			copiedEntity.setId(null);
			lastDate = lastDate.plus(interval, ChronoUnit.DAYS);
			if (interval == 30) {
				lastDate = lastDate.withDayOfMonth(fixedDay);
			}
			copiedEntity.setDataVencimento(lastDate);
		}
		
		copies.add(contaReceber);
		contaReceberRepository.saveAll(copies);
	}
}

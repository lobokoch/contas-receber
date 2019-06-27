/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria;		

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Optional;
import java.util.Collection;

import br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria.AgenciaBancariaAutoComplete;

import br.com.kerubin.api.financeiro.contasreceber.entity.agenciabancaria.AgenciaBancariaRepository;


 
@Service
public class ContaBancariaServiceImpl implements ContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private ContaBancariaListFilterPredicate contaBancariaListFilterPredicate;
	
	
	@Autowired
	private AgenciaBancariaRepository agenciaBancariaRepository;
	
	
	@Transactional
	@Override
	public ContaBancariaEntity create(ContaBancariaEntity contaBancariaEntity) {
		return contaBancariaRepository.save(contaBancariaEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaBancariaEntity read(java.util.UUID id) {
		return getContaBancariaEntity(id);
	}
	
	@Transactional
	@Override
	public ContaBancariaEntity update(java.util.UUID id, ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaEntity entity = getContaBancariaEntity(id);
		BeanUtils.copyProperties(contaBancariaEntity, entity, "id");
		entity = contaBancariaRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		contaBancariaRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<ContaBancariaEntity> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable) {
		Predicate predicate = contaBancariaListFilterPredicate.mountAndGetPredicate(contaBancariaListFilter);
		
		Page<ContaBancariaEntity> resultPage = contaBancariaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected ContaBancariaEntity getContaBancariaEntity(java.util.UUID id) {
		Optional<ContaBancariaEntity> contaBancariaEntity = contaBancariaRepository.findById(id);
		if (!contaBancariaEntity.isPresent()) {
			throw new IllegalArgumentException("ContaBancaria not found:" + id.toString());
		}
		return contaBancariaEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaAutoComplete> autoComplete(String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<AgenciaBancariaAutoComplete> agenciaBancariaAgenciaAutoComplete(String query) {
		Collection<AgenciaBancariaAutoComplete> result = agenciaBancariaRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(String query) {
		Collection<ContaBancariaNumeroContaAutoComplete> result = contaBancariaRepository.contaBancariaNumeroContaAutoComplete(query);
		return result;
	}
	
	
}

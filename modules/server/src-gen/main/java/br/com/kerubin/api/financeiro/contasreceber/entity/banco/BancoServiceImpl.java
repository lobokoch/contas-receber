/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.banco;		

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Optional;
import java.util.Collection;

 
@Service
public class BancoServiceImpl implements BancoService {
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private BancoListFilterPredicate bancoListFilterPredicate;
	
	
	@Transactional
	@Override
	public BancoEntity create(BancoEntity bancoEntity) {
		return bancoRepository.save(bancoEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public BancoEntity read(java.util.UUID id) {
		return getBancoEntity(id);
	}
	
	@Transactional
	@Override
	public BancoEntity update(java.util.UUID id, BancoEntity bancoEntity) {
		BancoEntity entity = getBancoEntity(id);
		BeanUtils.copyProperties(bancoEntity, entity, "id");
		entity = bancoRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		bancoRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<BancoEntity> list(BancoListFilter bancoListFilter, Pageable pageable) {
		Predicate predicate = bancoListFilterPredicate.mountAndGetPredicate(bancoListFilter);
		
		Page<BancoEntity> resultPage = bancoRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected BancoEntity getBancoEntity(java.util.UUID id) {
		Optional<BancoEntity> bancoEntity = bancoRepository.findById(id);
		if (!bancoEntity.isPresent()) {
			throw new IllegalArgumentException("Banco not found:" + id.toString());
		}
		return bancoEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<BancoAutoComplete> autoComplete(String query) {
		Collection<BancoAutoComplete> result = bancoRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<BancoNomeAutoComplete> bancoNomeAutoComplete(String query) {
		Collection<BancoNomeAutoComplete> result = bancoRepository.bancoNomeAutoComplete(query);
		return result;
	}
	
	
}

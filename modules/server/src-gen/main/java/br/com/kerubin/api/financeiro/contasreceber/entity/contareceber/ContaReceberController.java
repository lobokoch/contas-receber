/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.contareceber;

import java.util.Collection;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.kerubin.api.financeiro.contasreceber.common.PageResult;

		
import br.com.kerubin.api.financeiro.contasreceber.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contasreceber.entity.cliente.ClienteAutoComplete;



@RestController
@RequestMapping("entities/contaReceber")
public class ContaReceberController {
	
	@Autowired
	private ContaReceberService contaReceberService;
	
	@Autowired
	ContaReceberDTOConverter contaReceberDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<ContaReceber> create(@Valid @RequestBody ContaReceber contaReceber) {
		ContaReceberEntity contaReceberEntity = contaReceberService.create(contaReceberDTOConverter.convertDtoToEntity(contaReceber));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaReceberDTOConverter.convertEntityToDto(contaReceberEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
	public ResponseEntity<ContaReceber> read(@PathVariable java.util.UUID id) {
		try {
			ContaReceberEntity contaReceberEntity = contaReceberService.read(id);
			return ResponseEntity.ok(contaReceberDTOConverter.convertEntityToDto(contaReceberEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ContaReceber> update(@PathVariable java.util.UUID id, @Valid @RequestBody ContaReceber contaReceber) {
		try {
			ContaReceberEntity contaReceberEntity = contaReceberService.update(id, contaReceberDTOConverter.convertDtoToEntity(contaReceber));
			return ResponseEntity.ok(contaReceberDTOConverter.convertEntityToDto(contaReceberEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		contaReceberService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<ContaReceber> list(ContaReceberListFilter contaReceberListFilter, Pageable pageable) {
		Page<ContaReceberEntity> page = contaReceberService.list(contaReceberListFilter, pageable);
		List<ContaReceber> content = page.getContent().stream().map(pe -> contaReceberDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<ContaReceber> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<ContaReceberAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ContaReceberAutoComplete> result = contaReceberService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/contaReceberDescricaoAutoComplete")
	public Collection<ContaReceberDescricaoAutoComplete> contaReceberDescricaoAutoComplete(@RequestParam("query") String query) {
		Collection<ContaReceberDescricaoAutoComplete> result = contaReceberService.contaReceberDescricaoAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaReceberAgrupadorAutoComplete")
	public Collection<ContaReceberAgrupadorAutoComplete> contaReceberAgrupadorAutoComplete(@RequestParam("query") String query) {
		Collection<ContaReceberAgrupadorAutoComplete> result = contaReceberService.contaReceberAgrupadorAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaReceberSumFields")
	public ContaReceberSumFields getContaReceberSumFields(ContaReceberListFilter contaReceberListFilter) {
		ContaReceberSumFields result = contaReceberService.getContaReceberSumFields(contaReceberListFilter);
		return result;
	}
	
	
	@PutMapping("/actionBaixarContaComUmClique/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void actionBaixarContaComUmClique(@PathVariable java.util.UUID id) {
		try {
			contaReceberService.actionBaixarContaComUmClique(id);
		}
		catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PutMapping("/actionEstornarRecebimentoContaComUmClique/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void actionEstornarRecebimentoContaComUmClique(@PathVariable java.util.UUID id) {
		try {
			contaReceberService.actionEstornarRecebimentoContaComUmClique(id);
		}
		catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PostMapping("/actionFazerCopiasContaReceber")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void actionFazerCopiasContaReceber(@Valid @RequestBody ContaReceberMakeCopies contaReceberMakeCopies) {
		try {
			contaReceberService.actionFazerCopiasContaReceber(contaReceberMakeCopies);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
				
	// Begin relationships autoComplete 
	
	@Transactional(readOnly=true)
	@GetMapping("/planoContaPlanoContasAutoComplete")
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaAutoComplete> result = contaReceberService.planoContaPlanoContasAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/contaBancariaContaBancariaAutoComplete")
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaAutoComplete> result = contaReceberService.contaBancariaContaBancariaAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/cartaoCreditoCartaoCreditoAutoComplete")
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(@RequestParam("query") String query) {
		Collection<CartaoCreditoAutoComplete> result = contaReceberService.cartaoCreditoCartaoCreditoAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/clienteClienteAutoComplete")
	public Collection<ClienteAutoComplete> clienteClienteAutoComplete(@RequestParam("query") String query) {
		Collection<ClienteAutoComplete> result = contaReceberService.clienteClienteAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}

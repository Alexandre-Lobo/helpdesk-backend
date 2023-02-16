package com.alexandre.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alexandre.helpdesk.domain.Pessoa;
import com.alexandre.helpdesk.domain.Cliente;
import com.alexandre.helpdesk.domain.dtos.ClienteDto;
import com.alexandre.helpdesk.repository.PessoaRepository;
import com.alexandre.helpdesk.repository.ClienteRepository;
import com.alexandre.helpdesk.service.exceptions.DataIntegrationViolationException;
import com.alexandre.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById (Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException ("Objeto não encontrado! Id: " + id));
	}

	public ResponseEntity<List<ClienteDto>> findAll() {
		List<Cliente>list = repository.findAll();
		
		List<ClienteDto>listDto = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

	public Cliente create(ClienteDto objDto) {
		objDto.setId(null);
		
		validarCpfEEmail(objDto);
		Cliente newObj = new Cliente(objDto);
		
		return repository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDto objDto) {
		objDto.setId(id);
		Cliente oldObj = findById(id);
		validarCpfEEmail(objDto);
		oldObj = new Cliente(objDto);
		repository.save(oldObj);
		return oldObj;
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrationViolationException("Cliente possui chamados e não pode ser deletado!");
		}
		
		repository.delete(obj);
		
	}
	
	private void validarCpfEEmail(ClienteDto objDto) {
		Optional<Pessoa>obj = pessoaRepository.findByCpf(objDto.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrationViolationException("Cpf já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDto.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrationViolationException("Email já cadastrado no sistema");
		}
	}



	

}

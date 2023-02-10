package com.alexandre.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alexandre.helpdesk.domain.Pessoa;
import com.alexandre.helpdesk.domain.Tecnico;
import com.alexandre.helpdesk.domain.dtos.TecnicoDto;
import com.alexandre.helpdesk.repository.PessoaRepository;
import com.alexandre.helpdesk.repository.TecnicoRepository;
import com.alexandre.helpdesk.service.exceptions.DataIntegrationViolationException;
import com.alexandre.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById (Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException ("Objeto não encontrado! Id: " + id));
	}

	public ResponseEntity<List<TecnicoDto>> findAll() {
		List<Tecnico>list = tecnicoRepository.findAll();
		
		List<TecnicoDto>listDto = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

	public Tecnico create(TecnicoDto objDto) {
		objDto.setId(null);
		
		validarCpfEEmail(objDto);
		Tecnico newObj = new Tecnico(objDto);
		
		return tecnicoRepository.save(newObj);
	}

	private void validarCpfEEmail(TecnicoDto objDto) {
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

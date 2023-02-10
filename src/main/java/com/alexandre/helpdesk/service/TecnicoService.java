package com.alexandre.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alexandre.helpdesk.domain.Tecnico;
import com.alexandre.helpdesk.domain.dtos.TecnicoDto;
import com.alexandre.helpdesk.repository.TecnicoRepository;
import com.alexandre.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById (Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException ("Objeto não encontrado! Id: " + id));
	}

	public ResponseEntity<List<TecnicoDto>> findAll() {
		List<Tecnico>list = repository.findAll();
		
		List<TecnicoDto>listDto = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

}

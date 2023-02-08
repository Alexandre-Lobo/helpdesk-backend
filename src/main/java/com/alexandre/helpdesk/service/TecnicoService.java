package com.alexandre.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.helpdesk.domain.Tecnico;
import com.alexandre.helpdesk.repository.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById (Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElse(null);
	}

}

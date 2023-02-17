package com.alexandre.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.helpdesk.domain.Chamado;
import com.alexandre.helpdesk.repository.ChamadoRepository;
import com.alexandre.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById(Integer id) {
		Optional<Chamado>obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! ID = " + id ));
	}

	public List<Chamado> findAll() {
		List<Chamado>list = repository.findAll();
		if (list.size() == 0)
			throw new ObjectNotFoundException("Não há chamados cadastrados!");
		return list;
	}
	
	
}

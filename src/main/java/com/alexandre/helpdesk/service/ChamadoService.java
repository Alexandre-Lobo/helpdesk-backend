package com.alexandre.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.helpdesk.domain.Chamado;
import com.alexandre.helpdesk.domain.Cliente;
import com.alexandre.helpdesk.domain.Tecnico;
import com.alexandre.helpdesk.domain.dtos.ChamadoDto;
import com.alexandre.helpdesk.domain.enums.Prioridade;
import com.alexandre.helpdesk.domain.enums.Status;
import com.alexandre.helpdesk.repository.ChamadoRepository;
import com.alexandre.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	ChamadoRepository repository;
	
	@Autowired
	TecnicoService tecnicoService;
	@Autowired
	ClienteService  clienteService;
	
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

	public Chamado create(@Valid ChamadoDto objDto) {
		
		return repository.save(newChamado(objDto));
	}
	
	private Chamado newChamado(ChamadoDto obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		chamado.setPrioridade(Prioridade.ToEnum(obj.getPrioridade()));
		chamado.setStatus(Status.ToEnum(obj.getStatus()));
		
		return chamado;
		
	}
	
}

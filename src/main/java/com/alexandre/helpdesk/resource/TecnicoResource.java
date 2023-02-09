package com.alexandre.helpdesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.helpdesk.domain.Tecnico;
import com.alexandre.helpdesk.domain.dtos.TecnicoDto;
import com.alexandre.helpdesk.service.TecnicoService;

@RestController
@RequestMapping(value="/tecnico")
public class TecnicoResource {
	
	@Autowired 
	private TecnicoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		
		return ResponseEntity.ok().body(new TecnicoDto(obj));
		
	}

}

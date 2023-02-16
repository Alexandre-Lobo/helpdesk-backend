package com.alexandre.helpdesk.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexandre.helpdesk.domain.Cliente;
import com.alexandre.helpdesk.domain.dtos.ClienteDto;
import com.alexandre.helpdesk.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired 
	private ClienteService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDto(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll(){
		return service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto>create(@Valid @RequestBody ClienteDto obj){
		Cliente newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ClienteDto>update (@PathVariable Integer id, @Valid @RequestBody ClienteDto objDto){
		Cliente obj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ClienteDto(obj));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ClienteDto>delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

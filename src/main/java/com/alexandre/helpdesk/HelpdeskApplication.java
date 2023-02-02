package com.alexandre.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandre.helpdesk.domain.Chamado;
import com.alexandre.helpdesk.domain.Cliente;
import com.alexandre.helpdesk.domain.Tecnico;
import com.alexandre.helpdesk.domain.enums.Perfil;
import com.alexandre.helpdesk.domain.enums.Prioridade;
import com.alexandre.helpdesk.domain.enums.Status;
import com.alexandre.helpdesk.repository.ChamadoRepository;
import com.alexandre.helpdesk.repository.ClienteRepository;
import com.alexandre.helpdesk.repository.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Alexandre", "11111111111", "foo@fool.com", "123");
		tec1.addPerfil(Perfil.TECNICO);
		
		Cliente cli1 = new Cliente(null, "James", "22222222222", "oba@oba.com", "123");
		cli1.addPerfil(Perfil.CLIENTE);
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "1º Chamado", "Primeiro chamado", cli1, tec1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}

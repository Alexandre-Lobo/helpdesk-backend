package com.alexandre.helpdesk.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.alexandre.helpdesk.domain.enums.Prioridade;
import com.alexandre.helpdesk.domain.enums.Status;

public class Chamado {
	
	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private Prioridade prioridade;
	private Status status;
	private String titulo;
	private String observacoes;
	
	private Cliente cliente;
	private Tecnico tecnico;
	
	public Chamado() {
		super();
	}

	public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Cliente cliente,
			Tecnico tecnico) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.cliente = cliente;
		this.tecnico = tecnico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}

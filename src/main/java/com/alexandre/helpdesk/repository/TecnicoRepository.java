package com.alexandre.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}

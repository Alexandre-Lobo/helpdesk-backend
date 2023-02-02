package com.alexandre.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}

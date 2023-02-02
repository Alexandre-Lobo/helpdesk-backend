package com.alexandre.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

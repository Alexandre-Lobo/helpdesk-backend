package com.alexandre.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}

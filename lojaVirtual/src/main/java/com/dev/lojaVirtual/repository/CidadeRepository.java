package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
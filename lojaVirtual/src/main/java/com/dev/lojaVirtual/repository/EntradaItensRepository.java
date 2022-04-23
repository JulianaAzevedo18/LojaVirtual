package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.EntradaItens;

public interface EntradaItensRepository extends JpaRepository <EntradaItens, Long> {

	}
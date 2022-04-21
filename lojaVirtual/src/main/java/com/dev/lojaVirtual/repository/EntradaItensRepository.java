package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.EntradaItensModel;

public interface EntradaItensRepository extends JpaRepository <EntradaItensModel, Long> {

	}
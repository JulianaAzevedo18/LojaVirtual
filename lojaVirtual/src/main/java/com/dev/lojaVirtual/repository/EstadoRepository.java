package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.EstadoModel;

public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {

}
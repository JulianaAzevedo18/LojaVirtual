package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository <FuncionarioModel, Long>{

}

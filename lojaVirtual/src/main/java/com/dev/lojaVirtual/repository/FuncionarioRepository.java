package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long>{

}

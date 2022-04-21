package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

}
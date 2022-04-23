package com.dev.lojaVirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lojaVirtual.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
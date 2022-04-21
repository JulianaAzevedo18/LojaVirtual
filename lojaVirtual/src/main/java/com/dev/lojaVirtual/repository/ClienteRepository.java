package com.dev.lojaVirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.lojaVirtual.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

	@Query("from Cliente where email=?1")
	public List<ClienteModel> buscarClienteEmail(String email);
}
package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {

    PedidoModel findByUuid(UUID uuid);

    List<PedidoModel> findAll();

}

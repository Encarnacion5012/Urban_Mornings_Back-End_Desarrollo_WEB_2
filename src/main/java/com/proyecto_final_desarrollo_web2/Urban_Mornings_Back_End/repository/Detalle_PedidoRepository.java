package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Detalle_PedidoRepository extends JpaRepository<Detalle_Pedido, UUID> {
}

package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public interface Detalle_PedidoRepository extends JpaRepository<Detalle_Pedido, UUID> {

    @Modifying
    @Transactional
    @Query(value = "EXEC csp_RegistroDetallePedido :detalle_Id, :pedido_Id, :catalogo_id, :cantidad, :precio_Unitario, :sub_Total", nativeQuery = true)
    void registrarDetallePedido(
            @Param("detalle_Id") UUID detalle_Id,
            @Param("pedido_Id") UUID pedido_Id,
            @Param("catalogo_id") int catalogo_id,
            @Param("cantidad") int cantidad,
            @Param("precio_Unitario") BigDecimal precio_Unitario,
            @Param("sub_Total") BigDecimal sub_Total
    );

    boolean existsByDetalleId(UUID detalle_Id);
}
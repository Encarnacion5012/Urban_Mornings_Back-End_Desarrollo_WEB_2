package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    @Modifying
    @Transactional
    @Query(value = "EXEC csp_RegistroPedido :pedido_Id, :cliente_Id, :fecha_Hora_Retiro, :codigo_Retiro, :estado_Actual, :total_Pedido, :fecha_Creacion", nativeQuery = true)
    void registrarPedido(
            @Param("pedido_Id") UUID pedido_Id,
            @Param("cliente_Id") UUID cliente_Id,
            @Param("fecha_Hora_Retiro") LocalDateTime fecha_Hora_Retiro,
            @Param("codigo_Retiro") Long codigo_Retiro,
            @Param("estado_Actual") String estado_Actual,
            @Param("total_Pedido") BigDecimal total_Pedido,
            @Param("fecha_Creacion") LocalDateTime fecha_Creacion);

    boolean existsByCodigoRetiro(Long codigo_Retiro);


    @Query(value = """
        SELECT pa 
        FROM Pago pa 
        JOIN FETCH pa.pedido pe 
        LEFT JOIN FETCH pe.detallesPedido dp 
        LEFT JOIN FETCH dp.catalogo 
        WHERE pe.pedidoId = :id_pedido       
    """)
   Optional <PedidoResponseProjection> detallarPedido(@Param("id_pedido") UUID id_pedido);
}



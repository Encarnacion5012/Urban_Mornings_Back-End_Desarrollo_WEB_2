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
    SELECT
    pa.metodo_Pago AS paymentMethod,   
    pe.pedidoId AS id,
    pe.codigoRetiro AS withdrawalCode,
    CAST(pe.fecha_Hora_Retiro AS LOCALDATE ) AS pickupDate,
    CAST(pe.fecha_Hora_Retiro AS LOCALTIME ) AS pickupTime,
    pe.total_Pedido AS total,
    pe.dedicatoria AS dedication,
    pe.fecha_Creacion AS createdAt,
    dp.cantidad AS quantity,
    ca.catalogo_id AS catalogo_id,
    ca.nombre AS nombre,
    ca.precio AS precio,
    ca.tipo AS tipo,
    ca.imagen_Url AS imagen_Url  
    
     FROM Pago pa
     LEFT JOIN pa.pedido pe
     LEFT JOIN  pe.detallesPedido dp
     LEFT JOIN  dp.catalogo ca
     
     WHERE pa.pedido.pedidoId =:id_pedido     
     
     
    
""")
    PedidoResponseProjection detallarPedido(@Param("id_pedido") UUID id_pedido);
}



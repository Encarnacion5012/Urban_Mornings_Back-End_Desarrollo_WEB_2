package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
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
import java.util.List;

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
    Optional<PedidoResponseProjection> detallarPedido(@Param("id_pedido") UUID id_pedido);


    @Query(value = """
                SELECT DISTINCT pa FROM Pago pa
                JOIN FETCH pa.pedido pe
                LEFT JOIN FETCH pe.detallesPedido dp
                LEFT JOIN FETCH dp.catalogo ca

                where pe.cliente.clienteId =:id_cliente
            """)
    List<PedidoResponseProjection> listarPedidosPorcliente(@Param("id_cliente") UUID id_cliente);


    @Modifying
    @Transactional
    @Query(value = "UPDATE pedidos SET clienteid = :cliente_Id, fecha_hora_retiro = :fecha_Hora_Retiro, codigo_retiro = :codigo_Retiro, estado_actual = :estado_Actual, total_pedido = :total_Pedido, fecha_creacion = :fecha_Creacion WHERE pedido_id = :pedido_Id", nativeQuery = true)
    void actualizarPedido(
            @Param("pedido_Id") UUID pedido_Id,
            @Param("cliente_Id") UUID cliente_Id,
            @Param("fecha_Hora_Retiro") LocalDateTime fecha_Hora_Retiro,
            @Param("codigo_Retiro") Long codigo_Retiro,
            @Param("estado_Actual") String estado_Actual,
            @Param("total_Pedido") BigDecimal total_Pedido,
            @Param("fecha_Creacion") LocalDateTime fecha_Creacion);

    @Modifying
    @Transactional
    @Query(value = "EXEC csp_EliminarPedido :pedido_Id", nativeQuery = true)
    void eliminarPedido(@Param("pedido_Id") UUID pedido_Id);

    @Modifying
    @Transactional
    @Query(value = "EXEC pesp_Actualizar_Estado_Pedidos :id_Usuario, :nuevoEstado", nativeQuery = true)
    void actualizarEstadoPedido(
            @Param("id_Usuario") UUID id_usuario,
            @Param("nuevoEstado")String nuevoEstado
            );

}
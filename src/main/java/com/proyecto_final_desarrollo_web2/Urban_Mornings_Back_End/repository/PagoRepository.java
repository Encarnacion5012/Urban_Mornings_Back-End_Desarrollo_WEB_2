package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {

    @Modifying
    @Transactional
    @Query(value = "EXEC sp_insertar_pago :pago_id, :monto_pagado, :pedido_id, :metodo_pago, :transaccion_id, :fecha_pago", nativeQuery = true)
    void registrarPagoSP(
            @Param("pago_id") UUID pago_id,
            @Param("monto_pagado") BigDecimal monto_pagado,
            @Param("pedido_id") UUID pedido_id,
            @Param("metodo_pago") String metodo_pago,
            @Param("transaccion_id") String transaccion_id,
            @Param("fecha_pago") LocalDateTime fecha_pago
    );

    @Query(value = "SELECT * FROM pago", nativeQuery = true)
    List<Pago> listarTodos();

    @Modifying
    @Transactional
    @Query(value = "UPDATE pago SET monto_pagado = :monto_pagado, pedido_id = :pedido_id, metodo_pago = :metodo_pago, transaccion_id = :transaccion_id, fecha_pago = :fecha_pago WHERE pago_id = :pago_id", nativeQuery = true)
    void actualizarPago(
            @Param("pago_id") UUID pago_id,
            @Param("monto_pagado") BigDecimal monto_pagado,
            @Param("pedido_id") UUID pedido_id,
            @Param("metodo_pago") String metodo_pago,
            @Param("transaccion_id") String transaccion_id,
            @Param("fecha_pago") LocalDateTime fecha_pago
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pago WHERE pago_id = :pago_id", nativeQuery = true)
    void eliminarPago(@Param("pago_id") UUID pago_id);
}
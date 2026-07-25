package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public interface PagoRepository extends JpaRepository<Pago, UUID> {

    @Modifying
    @Query(value = "EXEC sp_insertar_pago :pago_id, :monto_pagado, :pedido_id, :metodo_pago, :transaccion_id, :fecha_pago", nativeQuery = true)
    void registrarPagoSP(
            @Param("pago_id") UUID pago_id,
            @Param("monto_pagado") BigDecimal monto_pagado,
            @Param("pedido_id") UUID pedido_id,
            @Param("metodo_pago") String metodo_pago,
            @Param("transaccion_id") String transaccion_id,
            @Param("fecha_pago") LocalDateTime fecha_pago
    );
}
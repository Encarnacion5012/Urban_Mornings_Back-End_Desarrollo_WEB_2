package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponsePagoDTO(
    UUID pago_id,
    BigDecimal monto_pagado,
    UUID pedido_id,
    String metodo_pago,
    String transaccion_id,
    LocalDateTime fecha_pago
) {
}
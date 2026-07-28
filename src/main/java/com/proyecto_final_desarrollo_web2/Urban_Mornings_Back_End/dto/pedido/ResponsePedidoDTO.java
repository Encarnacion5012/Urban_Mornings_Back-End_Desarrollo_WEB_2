package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponsePedidoDTO(
        UUID pedido_Id,
        Long codigo_Retiro,
        String estado_Actual,
        BigDecimal total_Pedido,
        LocalDateTime fecha_Hora_Retiro,
        LocalDateTime fecha_Creacion
) {
}
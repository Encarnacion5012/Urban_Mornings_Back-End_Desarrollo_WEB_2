package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponseDetallePedidoDTO(
        UUID detalle_Id,
        Integer catalogo_id,
        int cantidad,
        BigDecimal precio_Unitario,
        BigDecimal sub_Total
) {
}
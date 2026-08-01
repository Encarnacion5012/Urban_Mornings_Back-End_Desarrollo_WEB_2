package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RegistroDetallePedidoDTO(
        @NotNull 
        Integer catalogo_Id,

        @NotNull 
        @Min(value = 1, message = "La cantidad debe ser al menos 1") 
        Integer quantity

        
) {
}
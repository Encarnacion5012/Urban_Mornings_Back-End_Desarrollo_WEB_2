package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import jakarta.validation.constraints.NotNull;

public record ActulizarPedidoARetiradoDTO(

        @NotNull
        Long codigoRetiro
) {
}

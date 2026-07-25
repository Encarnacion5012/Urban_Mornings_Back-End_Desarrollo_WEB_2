package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegistroPedidoDTO(
        @NotNull 
        UUID cliente_Id,

        @NotNull 
        @Future(message = "La fecha de retiro debe ser en el futuro") 
        LocalDateTime fecha_Hora_Retiro

        // Nota: Cuando crees el DTO de los detalles, lo puedes agregar aquí así:
        // @NotEmpty @Valid List<RegistroDetallePedidoDTO> detalles
) {
}
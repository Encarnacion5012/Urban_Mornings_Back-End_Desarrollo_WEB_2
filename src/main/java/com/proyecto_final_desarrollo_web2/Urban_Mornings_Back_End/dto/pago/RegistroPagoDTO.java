package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record RegistroPagoDTO(
        @NotNull
        UUID pedido_id,

        @NotNull
        BigDecimal monto_Pagado,

        @NotNull
        String metodo_Pago, // Se recibe como texto para facilitar la conexión con el front

        String transaccion_Id // Puede ser nulo si el pago es en efectivo
) {
}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido.RegistroDetallePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Metodo_Pago;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record RegistroPedidoDTO(
        @NotNull 
        UUID cliente_Id,


        @NotNull(message = "La fecha de recogida no debe ser nula")
        @FutureOrPresent(message = "Lafecha recogida no puede ser pasado")
        LocalDate pickupDate,


        @NotNull(message = "La hora de recogida no debe ser nula")
        //@FutureOrPresent(message = "la hora de recogida no puede ser en el pasado")
        LocalTime pickupTime,


        String dedication,

        Metodo_Pago paymentMethod,

        @Valid
        List<RegistroDetallePedidoDTO> items

        // Nota: Cuando crees el DTO de los detalles, lo puedes agregar aquí así:
        // @NotEmpty @Valid List<RegistroDetallePedidoDTO> detalles
) {
}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseClienteDTO(
        UUID cliente_Id,
        String cedula,
        String nombre_Completo,
        String correo,
        String telefono,
        LocalDateTime fecha_Registro

) {
}

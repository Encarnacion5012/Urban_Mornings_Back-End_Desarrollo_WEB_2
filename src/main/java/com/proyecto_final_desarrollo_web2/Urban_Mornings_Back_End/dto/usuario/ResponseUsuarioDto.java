package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario;

import java.util.UUID;

public record ResponseUsuarioDto(
                UUID id_Usuario,
                String cedula,
                String nombre_Completo,
                String correo,
                String telefono,
                String imagen_url

) {
}

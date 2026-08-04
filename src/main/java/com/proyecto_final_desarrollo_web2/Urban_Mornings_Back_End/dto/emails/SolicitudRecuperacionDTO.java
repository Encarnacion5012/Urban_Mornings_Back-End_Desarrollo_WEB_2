package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SolicitudRecuperacionDTO(
        @NotBlank(message = "EL correo no debe estar en blanco ni ser nulo")
        @Email(message = "EL correo debe tener un formato valido")
        String correo
) {
}

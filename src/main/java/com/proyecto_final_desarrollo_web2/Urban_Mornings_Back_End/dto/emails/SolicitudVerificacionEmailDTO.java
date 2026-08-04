package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SolicitudVerificacionEmailDTO (
        @NotBlank(message = "El email no puede ser nulo")
        @Email(message = "El email debe cumplir con un formato valido")
        String email
){
}

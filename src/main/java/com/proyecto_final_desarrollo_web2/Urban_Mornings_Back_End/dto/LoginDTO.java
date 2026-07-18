package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
            @NotBlank @Email String email,
        @NotBlank String clave
) {
}

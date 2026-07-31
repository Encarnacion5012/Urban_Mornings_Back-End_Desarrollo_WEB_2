package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente_Usuario_Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record RegistroUsuarioDTO(
                @NotBlank(message = "EL nombre no debe estar en blanco") String nombre_Completo,

                @NotBlank(message = "EL numero de cedula no debe estar en blanco") @Pattern(regexp = "^\\d{3}-\\d{7}-\\d{1}$", message = "indudusca una cedula valida ejemplo: 000-0000000-0") String cedula,

                @NotBlank(message = "El campo email no debe estar en blanco") @Email(message = "EL campo email no cumple con el formato requerido example@example.example") String correo,

                @NotBlank(message = "El campo clave no de estar vacio") @Length(min = 8, max = 255, message = "La clave debe de tener mas de 8 caracteres") @Pattern(regexp = "^.*\\d.*$", message = "la clave debe tener al menos un numero del 1 al 9") @Pattern(regexp = "^.*[a-z].*$", message = "La clave debe tener al menos un la letra minuscula") @Pattern(regexp = "^.*[A-Z].*$", message = "La clave debe tener al menos una letra mayuscula") @Pattern(regexp = "^.*[\\W_].*$", message = "La clave debe tener al menos un caracter especial: (@!%#?&).") String clave,

                String imagen_url,

                Cliente_Usuario_Roles rol,

                @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "El teléfono debe tener un formato internacional válido (ej: +18095551234)") String telefono) {
}

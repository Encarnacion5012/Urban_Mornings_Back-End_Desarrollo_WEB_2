package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Categoria_Desayuno;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Tipo_Catalogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegistroCatalogoDTO(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotNull BigDecimal precio,
        Tipo_Catalogo tipo,
        Categoria_Desayuno categoria_Desayuno,
        @NotBlank String imagen_Url
) {
}

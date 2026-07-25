package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Categoria_Desayuno;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Tipo_Catalogo;

import java.math.BigDecimal;

public interface ResponseCatalogoProjection {
    Integer getCatalogo_id();

    String getNombre();
    String getDescripcion();
    BigDecimal getPrecio();
    Tipo_Catalogo getTipo();
    Categoria_Desayuno getCategoria_Desayuno();
    String getImagen_Url();
}

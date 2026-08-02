package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas;

import java.math.BigDecimal;
import java.util.UUID;

public interface Top5MasVendidoProjection {
Integer getVentas();
BigDecimal getIngresos();
Integer getId();
String getNombre();
String getImagen_Url();
}

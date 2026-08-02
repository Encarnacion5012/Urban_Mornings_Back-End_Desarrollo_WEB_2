package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas;

import java.math.BigDecimal;

public interface VentasPorMesAnioResponseProjection {
    String getMes();
    BigDecimal getTotalVentas();
    String getAnio();
}

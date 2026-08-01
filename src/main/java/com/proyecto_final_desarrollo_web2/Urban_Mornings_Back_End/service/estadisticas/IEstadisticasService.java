package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.estadisticas;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.VentasPorMesAnioResponseProjection;

import java.util.List;

public interface IEstadisticasService {
    public List<VentasPorMesAnioResponseProjection> listarEstadisticasVentasPorMes();
}

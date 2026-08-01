package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.estadisticas;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.VentasPorMesAnioResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImplEstadisticasService implements IEstadisticasService {
    private final PedidoRepository pedidoRepository;

    @Override
    public List<VentasPorMesAnioResponseProjection> listarEstadisticasVentasPorMes() {
        return pedidoRepository.listarEstadisticasVentasPorMes();
    }
}

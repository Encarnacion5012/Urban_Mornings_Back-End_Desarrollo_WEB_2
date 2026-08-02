package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.estadisticas;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.Top5MasVendidoProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.VentasPorMesAnioResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.Detalle_PedidoRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImplEstadisticasService implements IEstadisticasService {
    private final PedidoRepository pedidoRepository;
    private final Detalle_PedidoRepository detallePedidoRepository;

    @Override
    public List<VentasPorMesAnioResponseProjection> listarEstadisticasVentasPorMes() {
        return pedidoRepository.listarEstadisticasVentasPorMes();
    }
    @Override
    public List<Top5MasVendidoProjection> top5MasVendido() {
        return detallePedidoRepository.top5MasVendido();
    }
}

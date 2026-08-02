package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.Top5MasVendidoProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.VentasPorMesAnioResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.estadisticas.IEstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {

    private final IEstadisticasService estadisticasService;


    @GetMapping("/VentasPorMes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<VentasPorMesAnioResponseProjection>> listarEstadisticasVentasPorMes() {
        List<VentasPorMesAnioResponseProjection> ventasPorMes = estadisticasService.listarEstadisticasVentasPorMes();

        return ResponseEntity.ok(ventasPorMes);
    }

    @GetMapping("/top5Vendidos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Top5MasVendidoProjection>> top5Vendido() {
        List<Top5MasVendidoProjection> top5catalogos = estadisticasService.top5MasVendido();
        return ResponseEntity.ok(top5catalogos);
    }
}

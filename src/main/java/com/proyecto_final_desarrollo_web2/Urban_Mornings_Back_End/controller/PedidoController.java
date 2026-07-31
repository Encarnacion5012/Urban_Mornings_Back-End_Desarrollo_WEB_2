package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ActualizarEstadoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ResponsePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ValidarRetiroDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperServiceImpl;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido.IPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final IPedidoService pedidoService;
    private final PedidoMapperServiceImpl pedidoMapperService;

    @PostMapping("/registrar")
    ResponseEntity<PedidoResponseProjection> registrarPedido(@RequestBody @Valid RegistroPedidoDTO registroPedidoDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        PedidoResponseProjection pedido = pedidoService.registroPedido(registroPedidoDTO);
        URI uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<PedidoResponseProjection>> listarPedidos(@PathVariable UUID id) {
        List<PedidoResponseProjection> pedidos = pedidoService.listarPedidosPorcliente(id);
        return ResponseEntity.ok().body(pedidos);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponsePedidoDTO> actualizarPedido(@PathVariable UUID id,
            @RequestBody RegistroPedidoDTO registroDto) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, registroDto);
        return ResponseEntity.ok(pedidoMapperService.entityToResponseDTO(pedidoActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable UUID id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    // --- NUEVOS ENDPOINTS ---

    // Endpoint para actualizar cualquier estado manualmente (Ej. "EN_PREPARACION",
    // "EN_CAMINO")
    @PutMapping("/actualizar/{id}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable UUID id, @RequestBody @Valid ActualizarEstadoDTO dto) {
        pedidoService.actualizarEstado(id, dto.estado());
        return ResponseEntity.ok("Estado actualizado a " + dto.estado());
    }

    // Endpoint para validar el código del cliente y pasarlo a "RETIRADO"
    @PostMapping("/validar-retiro/{id}")
    public ResponseEntity<String> validarRetiro(@PathVariable UUID id, @RequestBody @Valid ValidarRetiroDTO dto) {
        pedidoService.validarRetiroPedido(id, dto.codigoRetiro());
        return ResponseEntity.ok("Código válido. Pedido entregado/retirado exitosamente.");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoResponseProjection>> listarTodosLosPedidos() {
        List<PedidoResponseProjection> pedidos = pedidoService.listarTodosLosPedidos();
        return ResponseEntity.ok().body(pedidos);
    }
}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;


import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.*;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperServiceImpl;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PedidoRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido.IPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final IPedidoService pedidoService;
    private final PedidoMapperServiceImpl pedidoMapperService;


@PostMapping("/registrar")
ResponseEntity<PedidoResponseProjection> registrarPedido(@RequestBody @Valid RegistroPedidoDTO registroPedidoDTO, UriComponentsBuilder uriComponentsBuilder) {
    PedidoResponseProjection pedido =  pedidoService.registroPedido(registroPedidoDTO);
    URI uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

    return ResponseEntity.created(uri).body(pedido);
}

@GetMapping("/listar/{id}")
    public ResponseEntity<List<PedidoResponseProjection>> listarPedidos(@PathVariable UUID id) {
    List<PedidoResponseProjection> pedidos = pedidoService.listarPedidosPorcliente(id);
    return ResponseEntity.ok().body(pedidos);
}


@GetMapping("/listar")
@PreAuthorize("hasAnyRole('ADMIN', 'PERSONAL_COCINA')")
ResponseEntity<List<PedidoResponseProjection>> listarPedidos() {
    List<PedidoResponseProjection> pedidos = pedidoService.listarPedidos();

    return ResponseEntity.ok(pedidos);
}

@GetMapping("/listar/view")
@PreAuthorize("hasAnyRole('ADMIN', 'PERSONAL_COCINA')")
ResponseEntity<List<PedidoResponseViewProjection>> listarPedidosViews() {
    List<PedidoResponseViewProjection> pedidos = pedidoService.listarPedidosParaViews();

    return ResponseEntity.ok(pedidos);
}


    @PutMapping("/actualizar/{id}/estado")
    public ResponseEntity<ResponsePedidoDTO> actualizarPedido(@PathVariable UUID id, @RequestBody ActualizarEstadoPedidoDTO nuevoEstadodto) {

    pedidoService.actulizarEstadoPedido(id, nuevoEstadodto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable UUID id) {

        pedidoService.eliminarPedido(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validar-retiro/{id}")
    public ResponseEntity<?> validarRetiro(@PathVariable UUID id, @RequestBody @Valid ActulizarPedidoARetiradoDTO actulizarDto) {
    Integer filasAfectadas = pedidoService.actulizarEstatoPedido_Retirado(id, actulizarDto.codigoRetiro());

    if (filasAfectadas==0) {
        return ResponseEntity.badRequest().body(Map.of("message", "EL codigo de retiro es incorrecto"));
    }

    return ResponseEntity.noContent().build();
    }
}


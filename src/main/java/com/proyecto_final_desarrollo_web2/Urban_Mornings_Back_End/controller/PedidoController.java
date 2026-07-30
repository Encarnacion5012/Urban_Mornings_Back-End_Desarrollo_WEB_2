package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;


import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
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

}

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponsePedidoDTO> actualizarPedido(@PathVariable UUID id, @RequestBody RegistroPedidoDTO registroDto) {

        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, registroDto);

        return ResponseEntity.ok(pedidoMapperService.entityToResponseDTO(pedidoActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable UUID id) {

        pedidoService.eliminarPedido(id);

        return ResponseEntity.noContent().build();
    }
}
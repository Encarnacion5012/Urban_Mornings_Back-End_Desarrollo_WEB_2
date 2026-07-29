package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ResponsePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/pedidos")
@RestController
@RequiredArgsConstructor
public class PedidoController {

    private final IPedidoService pedidoService;
    private final PedidoMapperService pedidoMapperService;

    @GetMapping("/todos")
    public ResponseEntity<List<ResponsePedidoDTO>> listarPedidos() {

        List<ResponsePedidoDTO> pedidos = pedidoService.listarTodos().stream()
                .map(pedidoMapperService::entityToResponseDTO)
                .toList();

        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponsePedidoDTO> registrarPedido(@RequestBody RegistroPedidoDTO registroDto) {

        Pedido pedidoCreado = pedidoService.registroPedido(registroDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoMapperService.entityToResponseDTO(pedidoCreado));
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
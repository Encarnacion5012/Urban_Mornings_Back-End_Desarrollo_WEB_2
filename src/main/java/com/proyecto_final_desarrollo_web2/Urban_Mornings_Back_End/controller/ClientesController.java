package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.ClienteMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes.IClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/clientes")
@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final IClientesService clientesService;
    private final ClienteMapperService clienteMapperService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<List<ResponseClienteProjection>> listarClientes(){

        List<ResponseClienteProjection> clientes = clientesService.listarTodos();

        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponseClienteProjection> registrarCliente(@RequestBody RegistroClienteDTO registroDto) {

        ResponseClienteProjection clienteCreado = clientesService.registroClientes(registroDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteCreado);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseClienteDTO> actualizarCliente(@PathVariable UUID id, @RequestBody RegistroClienteDTO registroDto) {

        Cliente clienteActualizado = clientesService.actualizarCliente(id, registroDto);

        return ResponseEntity.ok(clienteMapperService.entityToResponseDto(clienteActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable UUID id) {

        clientesService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}
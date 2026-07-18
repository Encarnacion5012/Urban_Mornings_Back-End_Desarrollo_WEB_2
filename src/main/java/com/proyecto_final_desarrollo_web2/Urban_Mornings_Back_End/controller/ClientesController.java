package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;


import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.ClienteMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/clientes")
@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteRepository clienteRepository;
    private final ClienteMapperService clienteMapperService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<List<ResponseClienteDTO>> listarClientes(){

        List<ResponseClienteDTO> clientes = clienteRepository.listarClientes().stream()
                .map(clienteMapperService::entityToResponseDto)
                .toList();

        return ResponseEntity.ok(clientes);
    }

}

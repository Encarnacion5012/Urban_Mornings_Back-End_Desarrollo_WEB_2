package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago.RegistroPagoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago.ResponsePagoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PagoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pago.IPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/pagos")
@RestController
@RequiredArgsConstructor
public class PagoController {

    private final IPagoService pagoService;
    private final PagoMapperService pagoMapperService;

    @GetMapping("/todos")
    public ResponseEntity<List<ResponsePagoDTO>> listarPagos() {

        List<ResponsePagoDTO> pagos = pagoService.listarTodos().stream()
                .map(pagoMapperService::entityToResponseDTO)
                .toList();

        return ResponseEntity.ok(pagos);
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponsePagoDTO> registrarPago(@RequestBody RegistroPagoDTO registroDto) {

        Pago pagoCreado = pagoService.registroPago(registroDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoMapperService.entityToResponseDTO(pagoCreado));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponsePagoDTO> actualizarPago(@PathVariable UUID id, @RequestBody RegistroPagoDTO registroDto) {

        Pago pagoActualizado = pagoService.actualizarPago(id, registroDto);

        return ResponseEntity.ok(pagoMapperService.entityToResponseDTO(pagoActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable UUID id) {

        pagoService.eliminarPago(id);

        return ResponseEntity.noContent().build();
    }
}
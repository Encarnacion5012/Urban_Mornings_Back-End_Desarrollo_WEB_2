package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;


import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final IPedidoService pedidoService;




}

package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapperService mapperService;

    @Override
    @Transactional
    public Pedido registroPedido(RegistroPedidoDTO registroPedidoDTO) {
        
        // 1. Usamos tu Mapper de MapStruct para traducir
        Pedido nuevoPedido = mapperService.registroDTOToEntity(registroPedidoDTO);
        
        // 2. Guardamos el pedido en SQL
        return pedidoRepository.save(nuevoPedido);
    }
}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.detalle_pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido.RegistroDetallePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.Detalle_PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.Detalle_PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Detalle_PedidoServiceImpl implements IDetalle_PedidoService {

    private final Detalle_PedidoRepository detallePedidoRepository;
    private final Detalle_PedidoMapperService mapperService;

    @Override
    @Transactional
    public Detalle_Pedido registroDetallePedido(RegistroDetallePedidoDTO detallePedidoDTO) {

        // 1. Usamos tu Mapper automático de MapStruct
        Detalle_Pedido detalle = mapperService.registroDTOToEntity(detallePedidoDTO);


        return detallePedidoRepository.save(detalle);
    }
}
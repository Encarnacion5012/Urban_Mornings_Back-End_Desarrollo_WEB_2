package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ResponsePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapperService {

    @Mapping(target = "cliente.clienteId", source = "cliente_Id")
    @Mapping(target = "pedido_Id", ignore = true)
    @Mapping(target = "fecha_Hora_Retiro", expression = "java(dto.pickupDate().atTime(dto.pickupTime()))" )
    @Mapping(target = "codigo_Retiro", ignore = true)
    @Mapping(target = "estado_Actual", constant = "PENDIENTE")
    @Mapping(target = "total_Pedido", ignore = true)
    @Mapping(target = "fecha_Creacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "detallesPedido", ignore = true)
    Pedido registroDTOToEntity(RegistroPedidoDTO dto);

    ResponsePedidoDTO entityToResponseDTO(Pedido pedido);
}
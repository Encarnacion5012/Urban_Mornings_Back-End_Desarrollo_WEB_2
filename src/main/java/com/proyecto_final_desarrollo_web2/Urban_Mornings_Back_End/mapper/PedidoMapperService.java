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
    @Mapping(target = "codigo_Retiro", ignore = true)
    @Mapping(target = "estado_Actual", ignore = true)
    @Mapping(target = "total_Pedido", ignore = true)
    @Mapping(target = "fecha_Creacion", ignore = true)
    @Mapping(target = "detallesPedido", ignore = true)
    Pedido registroDTOToEntity(RegistroPedidoDTO dto);

    ResponsePedidoDTO entityToResponseDTO(Pedido pedido);
}
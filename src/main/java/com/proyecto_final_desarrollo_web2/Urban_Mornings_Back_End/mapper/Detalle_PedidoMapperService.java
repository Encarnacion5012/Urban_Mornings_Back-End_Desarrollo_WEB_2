package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido.RegistroDetallePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido.ResponseDetallePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Detalle_PedidoMapperService {

    @Mapping(target = "catalogo.catalogoId", source = "catalogoId")
    @Mapping(target = "detalleId", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "cantidad", source = "dto.cantidad")
    @Mapping(target = "precio_Unitario", ignore = true)
    @Mapping(target = "sub_Total", ignore = true)
    Detalle_Pedido registroDTOToEntity(RegistroDetallePedidoDTO dto);

    @Mapping(target = "catalogo_id", source = "catalogo.catalogoId")
    ResponseDetallePedidoDTO entityToResponseDTO(Detalle_Pedido detalle);
}
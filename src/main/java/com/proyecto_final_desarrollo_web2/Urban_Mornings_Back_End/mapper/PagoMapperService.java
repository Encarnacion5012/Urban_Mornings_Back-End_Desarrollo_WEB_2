package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago.RegistroPagoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago.ResponsePagoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PagoMapperService {

    @Mapping(target = "pedido.pedidoId", source = "pedido_id")
    @Mapping(target = "pago_Id", ignore = true)
    @Mapping(target = "fecha_Pago", ignore = true)
    Pago registroDTOToEntity(RegistroPagoDTO dto);

    @Mapping(target = "pedido_id", source = "pedido.pedidoId")
    ResponsePagoDTO entityToResponseDTO(Pago pago);

}
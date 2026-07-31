package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapperService {

    @Mapping(target = "clienteId", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "rol", constant = "CLIENTE")
    @Mapping(target = "fecha_Registro", expression = "java(java.time.LocalDateTime.now())")
    // MapStruct pasará la 'clave' automáticamente sin encriptarla aquí
    @Mapping(target = "nombreCompleto", source = "nombre_Completo")
    Cliente registerDtoTOEntity(RegistroClienteDTO registroDto);

    @Mapping(target = "telefono", source = "cliente.telefono")
    @Mapping(target = "cliente_Id", source = "cliente.clienteId")
    ResponseClienteDTO entityToResponseDto(Cliente cliente);
}
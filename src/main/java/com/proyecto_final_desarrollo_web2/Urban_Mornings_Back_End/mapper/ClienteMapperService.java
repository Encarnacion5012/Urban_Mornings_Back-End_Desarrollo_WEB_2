package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class ClienteMapperService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "clienteId", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "rol", constant = "CLIENTE")
    @Mapping(target = "fecha_Registro", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "clave", expression = "java(passwordEncoder.encode(registroDto.clave()))")
    @Mapping(target = "nombreCompleto", source = "nombre_Completo")
    public abstract Cliente registerDtoTOEntity(RegistroClienteDTO registroDto);

    @Mapping(target = "telefono", source = "cliente.telefono")
    @Mapping(target = "cliente_Id", source = "cliente.clienteId")
    public abstract ResponseClienteDTO entityToResponseDto(Cliente cliente);
}
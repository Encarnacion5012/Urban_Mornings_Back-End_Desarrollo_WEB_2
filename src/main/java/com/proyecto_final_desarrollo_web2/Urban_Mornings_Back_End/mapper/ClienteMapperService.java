package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ActuializarClientesDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ClienteMapperService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "clienteId", ignore = true)
    @Mapping(target = "rol", constant = "CLIENTE")
    @Mapping(target = "fecha_Registro", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "clave", expression = ("java(passwordEncoder.encode(registroDto.clave()))"))
    @Mapping(target = "nombreCompleto", source = "nombre_Completo")
    public abstract Cliente registerDtoTOEntity(RegistroClienteDTO registroDto);

    @Mapping(target = "telefono", source = "cliente.telefono")
    @Mapping(target = "cliente_Id", source = "cliente.clienteId")
    public abstract ResponseClienteDTO entityToResponseDto(Cliente cliente);

    @Mapping(target = "clave", source = "clave", qualifiedByName = "encodePassword")
    public abstract void actualizarClientes(ActuializarClientesDTO actulizacionDTO, @MappingTarget Cliente cliente);
    @Named("encodePassword")
    protected String encodePassword(String clave) {
        return passwordEncoder.encode(clave);
    }
    @Condition
    public boolean isNotEmpty(String value){
        return value != null && !value.trim().isEmpty();
    }
}

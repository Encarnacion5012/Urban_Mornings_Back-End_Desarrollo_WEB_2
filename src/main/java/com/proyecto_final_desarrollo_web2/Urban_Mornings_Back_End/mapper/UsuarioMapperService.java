package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapperService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "clave", expression = "java(passwordEncoder.encode(usuarioDTO.clave()))")
    public abstract Usuario registroDTOToEntity(RegistroUsuarioDTO usuarioDTO);

    @Mapping(target = "idUsuario", source = "usuario.idUsuario")
    public abstract ResponseUsuarioDto entityToResponseDto(Usuario usuario);
}
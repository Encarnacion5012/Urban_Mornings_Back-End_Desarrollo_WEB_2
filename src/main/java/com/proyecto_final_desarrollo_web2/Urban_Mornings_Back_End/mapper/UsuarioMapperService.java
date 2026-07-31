package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ActualizarUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)


public abstract class UsuarioMapperService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "clave", expression = "java(passwordEncoder.encode(usuarioDTO.clave()))")
    public abstract Usuario registroDTOToEntity(RegistroUsuarioDTO usuarioDTO);

    @Mapping(target = "id_Usuario", source = "usuario.idUsuario")
    public abstract ResponseUsuarioDto entityToResponseDto(Usuario usuario);


    @Mapping(target = "clave", source = "clave", qualifiedByName = "encodePassword")
     public abstract void  actualizarUsuarioDesdeDtoActualizacion(ActualizarUsuarioDTO actualizaciondto, @MappingTarget Usuario usuario);

    @Named("encodePassword")
    protected String encodePassword(String clave) {
        return passwordEncoder.encode(clave);
    }
     @Condition
    public boolean isNotEmpty(String value){
         return value != null && !value.trim().isEmpty();
     }
}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapperService {

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    // Se eliminó la encriptación de la clave. MapStruct la pasará en texto plano automáticamente.
    Usuario registroDTOToEntity(RegistroUsuarioDTO usuarioDTO);

    @Mapping(target = "idUsuario", source = "usuario.idUsuario")
    ResponseUsuarioDto entityToResponseDto(Usuario usuario);
}
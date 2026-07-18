package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.RegistroCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.ResponseCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface CatalogoMapperService {

    @Mapping(target = "catalogo_id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    Catalogo resgistroDTOToEntity(RegistroCatalogoDTO catalogoDTO);


    @Mapping(target = "catalogo_id", source = "catalogo_id")
    ResponseCatalogoDTO entityToDTO(Catalogo catalogo);
}

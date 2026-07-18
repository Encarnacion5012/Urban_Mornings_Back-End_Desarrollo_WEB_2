package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.RegistroCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.ResponseCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.CatalogoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.catalogo.ICatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogo")
@RequiredArgsConstructor
public class CatalogoController {
    private final ICatalogoService catalogoService;
    private final CatalogoMapperService catalogoMapperService;

    @PostMapping("/registar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registrarCatalogo(@RequestBody @Valid RegistroCatalogoDTO catalogoDTO) {
        Catalogo catalogo = catalogoService.registroCatalgo(catalogoDTO);

        return ResponseEntity.ok().body(catalogoMapperService.entityToDTO(catalogo));

    }


    @GetMapping("/listar")
    public ResponseEntity<List<ResponseCatalogoDTO>> listarCatalogos(){
        List<ResponseCatalogoDTO> catalogos = catalogoService.listarTodos().stream()
                .map(catalogoMapperService::entityToDTO)
                .toList();

        return ResponseEntity.ok().body(catalogos);
    }
}

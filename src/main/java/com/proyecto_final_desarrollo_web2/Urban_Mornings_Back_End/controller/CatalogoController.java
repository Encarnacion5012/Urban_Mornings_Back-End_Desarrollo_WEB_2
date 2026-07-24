package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.RegistroCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.ResponseCatalogoProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.CatalogoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.catalogo.ICatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
@RequiredArgsConstructor
public class CatalogoController {
    private final ICatalogoService catalogoService;
    private final CatalogoMapperService catalogoMapperService;

    @PostMapping("/registar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseCatalogoProjection> registrarCatalogo(@RequestBody @Valid RegistroCatalogoDTO catalogoDTO) {
        ResponseCatalogoProjection catalogo = catalogoService.registroCatalgo(catalogoDTO);

        return ResponseEntity.ok().body(catalogo);

    }


    @GetMapping("/listar")
    public ResponseEntity<List<ResponseCatalogoProjection>> listarCatalogos(){
        List<ResponseCatalogoProjection> catalogos = catalogoService.listarTodos();

        return ResponseEntity.ok().body(catalogos);
    }
}

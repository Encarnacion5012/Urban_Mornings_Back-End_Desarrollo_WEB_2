package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ActualizarUsuarioDTO;


import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private  final IUsuarioService usuarioService;

    private final UsuarioMapperService usuarioMapperService;


    @GetMapping("/listar/{idUsuarioACtual}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ResponseUsuarioProjection>> listarTodosUsuarios(@PathVariable UUID idUsuarioACtual) {
        List<ResponseUsuarioProjection> usuarios = usuarioService.listarUsuarios(idUsuarioACtual );
        return ResponseEntity.ok().body(usuarios);
    }


    @PostMapping("/registro")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseUsuarioProjection> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroDto, UriComponentsBuilder uriBuilder) {

        ResponseUsuarioProjection usuarioCreado = usuarioService.registroUsuario(registroDto);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioCreado.getId_usuario()).toUri();
        return ResponseEntity.created(uri).body(usuarioCreado);
    }


    //Metodo para actualizar otros usuarios reservado para admins
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/otros/{id}")
    public ResponseEntity<ResponseUsuarioDto> actualizarUsuario(@PathVariable UUID id,
                                                                @RequestBody @Valid ActualizarUsuarioDTO registroDto) {

        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, registroDto);

        return ResponseEntity.ok(usuarioMapperService.entityToResponseDto(usuarioActualizado));
    }

    //metodo para actualizar profile de usuarios

    @PreAuthorize("hasAnyRole('ADMIN','PERSONAL_COCINA')")
    @PutMapping("/actualizar/profile/{id}")
    public ResponseEntity<ResponseUsuarioDto> actualizarProfile(@PathVariable UUID id, @RequestBody @Valid ActualizarUsuarioDTO registroDto) {

        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, registroDto);

        return ResponseEntity.ok(usuarioMapperService.entityToResponseDto(usuarioActualizado));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable UUID id) {

        Integer filasAfectadas = usuarioService.eliminarUsuario(id);

        if (filasAfectadas == 0) {
            return ResponseEntity.badRequest().body(Map.of("message","Error al eliminar usuario, intente mas tarde"));
        }

        return ResponseEntity.noContent().build();
    }
}
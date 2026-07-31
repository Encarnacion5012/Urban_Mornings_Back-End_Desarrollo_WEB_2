package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;



import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private  final IUsuarioService usuarioService;

    private final UsuarioMapperService usuarioMapperService;


    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ResponseUsuarioProjection>> listarTodosUsuarios() {
        List<ResponseUsuarioProjection> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }


    @PostMapping("/registro")
    public ResponseEntity<ResponseUsuarioProjection> registrarUsuario(@RequestBody RegistroUsuarioDTO registroDto) {

        ResponseUsuarioProjection usuarioCreado = usuarioService.registroUsuario(registroDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioCreado);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseUsuarioDto> actualizarUsuario(@PathVariable UUID id,
                                                                @RequestBody RegistroUsuarioDTO registroDto) {

        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, registroDto);

        return ResponseEntity.ok(usuarioMapperService.entityToResponseDto(usuarioActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
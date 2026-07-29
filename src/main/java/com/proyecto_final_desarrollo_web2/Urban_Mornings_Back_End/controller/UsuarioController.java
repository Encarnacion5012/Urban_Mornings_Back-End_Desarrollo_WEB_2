package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;
    private final UsuarioMapperService usuarioMapperService;

    @GetMapping("/todos")
    public ResponseEntity<List<ResponseUsuarioDto>> listarUsuarios() {

        List<ResponseUsuarioDto> usuarios = usuarioService.listarTodos().stream()
                .map(usuarioMapperService::entityToResponseDto)
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponseUsuarioDto> registrarUsuario(@RequestBody RegistroUsuarioDTO registroDto) {

        Usuario usuarioCreado = usuarioService.registroUsuario(registroDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioMapperService.entityToResponseDto(usuarioCreado));
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
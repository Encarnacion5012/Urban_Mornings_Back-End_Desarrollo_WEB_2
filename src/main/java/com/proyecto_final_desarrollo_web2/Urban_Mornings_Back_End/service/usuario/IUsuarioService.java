package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {
    
    Usuario registroUsuario(RegistroUsuarioDTO dto);
    
    List<Usuario> listarTodos();
    
    Usuario actualizarUsuario(UUID id_usuario, RegistroUsuarioDTO dto);
    
    void eliminarUsuario(UUID id_usuario);
}
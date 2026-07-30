package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {
    public ResponseUsuarioProjection registroUsuario(RegistroUsuarioDTO dto);
    public List<ResponseUsuarioProjection> listarUsuarios();
    Usuario actualizarUsuario(UUID id_usuario, RegistroUsuarioDTO dto);

    void eliminarUsuario(UUID id_usuario);
}






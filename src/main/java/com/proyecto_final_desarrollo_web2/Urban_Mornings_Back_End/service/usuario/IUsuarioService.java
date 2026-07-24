package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;

import java.util.UUID;

public interface IUsuarioService {
    public UUID registroUsuario(RegistroUsuarioDTO dto);
}

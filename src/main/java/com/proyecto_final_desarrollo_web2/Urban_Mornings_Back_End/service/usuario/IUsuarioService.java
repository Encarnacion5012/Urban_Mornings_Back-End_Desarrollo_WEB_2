package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;

public interface IUsuarioService {
    public Usuario registroUsuario(RegistroUsuarioDTO dto);
}

package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario;

import java.util.UUID;

public interface ResponseUsuarioProjection {
    UUID getId_usuario();
    String getCedula();
    String getNombre_Completo();
    String getCorreo();
    String getTelefono();
}

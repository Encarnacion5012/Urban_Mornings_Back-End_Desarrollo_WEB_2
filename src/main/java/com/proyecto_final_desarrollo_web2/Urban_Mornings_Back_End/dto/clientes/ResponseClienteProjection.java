package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente_Usuario_Roles;

import java.util.UUID;

public interface ResponseClienteProjection {
    UUID getCliente_Id();
    String getCedula();
    String getNombre_Completo();
    String getCorreo();
    String getTelefono();
    Cliente_Usuario_Roles getRol();
    String getImagen_url();
}

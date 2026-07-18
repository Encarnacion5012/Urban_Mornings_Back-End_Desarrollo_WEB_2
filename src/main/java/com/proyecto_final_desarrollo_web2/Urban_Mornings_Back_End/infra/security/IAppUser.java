package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IAppUser extends UserDetails {
    UUID getUserId();
    String getNombre_Completo();
    String getUrl_Imagen();

}

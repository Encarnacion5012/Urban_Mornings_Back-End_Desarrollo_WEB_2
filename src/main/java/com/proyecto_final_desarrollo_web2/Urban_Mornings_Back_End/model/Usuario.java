package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.security.IAppUser;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "idUsuario")

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails, IAppUser {
    @Id
    @Column(name = "id_usuario")
    private UUID idUsuario;

    private String cedula;

    private String nombre_Completo;
    private String correo;
    private String clave;

    private String imagen_url;

    @Enumerated(EnumType.STRING)
    private Cliente_Usuario_Roles rol;

    private boolean activo;

    private String telefono;

    @Override
    public String getUsername() {
        return getCorreo();
    }

    @Override
    public @Nullable String getPassword() {
        return getClave();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getUrl_Imagen() {
        return this.getImagen_url();
    }

    @Override
    public UUID getUserId() {
        return this.getIdUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.activo; // Vinculamos tu campo 'activo' de la base de datos
    }
}

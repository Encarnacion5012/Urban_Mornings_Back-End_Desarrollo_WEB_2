package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.security.IAppUser;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "clienteId")
@Entity
@Table(name = "clientes")
public class Cliente implements UserDetails, IAppUser {
    
    @Id
    @Column(name = "cliente_Id")
    private UUID clienteId;

    private String cedula;
    
    @Column(name = "nombre_Completo")
    private String nombreCompleto;
    
    private String correo;
    private String telefono;
    private String clave;
    private String imagen_url;

    @Enumerated(EnumType.STRING)
    private Cliente_Usuario_Roles rol;
    
    private LocalDateTime fecha_Registro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Pedido> pedidos;

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
        return List.of(new SimpleGrantedAuthority(rol.name()));  //no se necesita prefijo "ROLE_" ya este de prefijo en la onfiguracion de seguiridad
    }

    // Implementación de los métodos de IAppUser
    @Override
    public String getUrl_Imagen() {
        return this.getImagen_url();
    }

    @Override
    public String getNombre_Completo() {
        return this.getNombreCompleto();
    }

    @Override
    public UUID getUserId() {
        return this.getClienteId();
    }

    // MÉTODOS OBLIGATORIOS DE USERDETAILS FALTANTES
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
        return true; 
    }
}
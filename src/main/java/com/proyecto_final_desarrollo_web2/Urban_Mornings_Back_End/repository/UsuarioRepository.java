package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    //Implemtado como optional para mos comidadad en el authenticationService
    Optional<UserDetails> findByCorreo(String correo);

    boolean existsByIdUsuario(UUID id);



    //Llamamos al storaged procedue con los parametros
    @Query(value = """
        EXEC usp_RegistroUsuario 
        :id_usuario,
        :cedula,
        :nombre_Completo,
        :correo,
        :telefono,
        :clave,
        :rol,
        :imagen_url,
        :activo
""", nativeQuery = true)
    Usuario registarUsuario(
           @Param("id_usuario") UUID id_usuario,
           @Param("cedula") String cedula,
           @Param("nombre_Completo") String nombre_Completo,
           @Param("correo") String correo,
           @Param("telefono") String telefono,
           @Param("clave") String clave,
           @Param("rol") String rol,
           @Param("imagen_url") String imagen_url,
           @Param("activo") boolean activo
    );
}

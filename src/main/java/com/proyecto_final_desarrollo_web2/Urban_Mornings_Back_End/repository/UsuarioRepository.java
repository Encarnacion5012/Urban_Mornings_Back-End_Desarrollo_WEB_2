package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    ResponseUsuarioProjection registarUsuario(
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


    @Query(value = "EXEC usp_ListarUsuarios", nativeQuery = true)
    List<ResponseUsuarioProjection>listarUsuarios();




    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET cedula = :cedula, nombre_Completo = :nombre_Completo, correo = :correo, telefono = :telefono, clave = :clave, rol = :rol, imagen_url = :imagen_url, activo = :activo WHERE id_usuario = :id_usuario", nativeQuery = true)
    void actualizarUsuario(
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

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE id_usuario = :id_usuario", nativeQuery = true)
    void eliminarUsuario(@Param("id_usuario") UUID id_usuario);
}
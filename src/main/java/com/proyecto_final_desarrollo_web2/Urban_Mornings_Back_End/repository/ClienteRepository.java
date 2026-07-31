package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    
    // CORRECCIÓN 1: Devolver Optional<Cliente> en lugar de Optional<UserDetails>
    Optional<Cliente> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    @Modifying
    @Transactional
    @Query(value = """
        SET NOCOUNT ON;
        EXEC csp_RegistroCLientes :id, :cedula, :nombre_Completo, :correo, :telefono, :clave, :rol, :imagen_url, :fecha_Registro
    """, nativeQuery = true)
    void registrarCLiente (
            @Param("id") UUID id,
            @Param("cedula") String cedula,
            @Param("nombre_Completo") String nombre_Completo,
            @Param("correo") String correo,
            @Param("telefono") String telefono,
            @Param("clave") String clave,
            @Param("rol") String rol,
            @Param("imagen_url") String imagen_url,
            @Param("fecha_Registro") LocalDateTime fecha_Registro
    );

    @Query(value = "EXEC csp_ListarClientes", nativeQuery = true)
    List<Cliente> listarClientes();

    @Modifying
    @Transactional
    // CORRECCIÓN 2: Se cambió 'WHERE id = :id' a 'WHERE cliente_Id = :id'
    @Query(value = "UPDATE clientes SET cedula = :cedula, nombre_Completo = :nombre_Completo, correo = :correo, telefono = :telefono, clave = :clave, rol = :rol, imagen_url = :imagen_url WHERE cliente_Id = :id", nativeQuery = true)
    void actualizarCliente(
            @Param("id") UUID id,
            @Param("cedula") String cedula,
            @Param("nombre_Completo") String nombre_Completo,
            @Param("correo") String correo,
            @Param("telefono") String telefono,
            @Param("clave") String clave,
            @Param("rol") String rol,
            @Param("imagen_url") String imagen_url
    );

    @Modifying
    @Transactional
    // CORRECCIÓN 2: Se cambió 'WHERE id = :id' a 'WHERE cliente_Id = :id'
    @Query(value = "DELETE FROM clientes WHERE cliente_Id = :id", nativeQuery = true)
    void eliminarCliente(@Param("id") UUID id);
}
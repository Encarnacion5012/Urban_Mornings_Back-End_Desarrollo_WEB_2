package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    
    // Implementado como optional para más comodidad en el authenticationService
    Optional<UserDetails> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    boolean existsByClienteId(UUID clienteId);

    // Agregamos las anotaciones obligatorias y cambiamos Cliente por void

    @Transactional
    @Query(value = """
        EXEC  csp_RegistroCLientes :id, :cedula, :nombre_Completo, :correo, :telefono, :clave, :rol, :imagen_url, :fecha_Registro
    """, nativeQuery = true)
    Cliente registrarCLiente (
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
    @Query(value = "UPDATE clientes SET cedula = :cedula, nombre_Completo = :nombre_Completo, correo = :correo, telefono = :telefono, clave = :clave, rol = :rol, imagen_url = :imagen_url WHERE id = :id", nativeQuery = true)
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
    @Query(value = "DELETE FROM clientes WHERE id = :id", nativeQuery = true)
    void eliminarCliente(@Param("id") UUID id);
}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;
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

    Optional<Cliente> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    @Modifying
    @Transactional
    @Query(value = """
                SET NOCOUNT ON;
                EXEC csp_RegistroCLientes :id, :cedula, :nombre_Completo, :correo, :telefono, :clave, :rol, :imagen_url, :fecha_Registro
            """, nativeQuery = true)
    void registrarCLiente(
            @Param("id") UUID id,
            @Param("cedula") String cedula,
            @Param("nombre_Completo") String nombre_Completo,
            @Param("correo") String correo,
            @Param("telefono") String telefono,
            @Param("clave") String clave,
            @Param("rol") String rol,
            @Param("imagen_url") String imagen_url,
            @Param("fecha_Registro") LocalDateTime fecha_Registro);

    // CAMBIADO: Ahora devuelve la Proyección en lugar del Cliente puro
    @Query(value = "EXEC csp_ListarClientes", nativeQuery = true)
    List<ResponseClienteProjection> listarClientes();

    // NUEVO: Nos servirá para devolver el cliente recién guardado en el Service
    @Query(value = "SELECT * FROM clientes WHERE cliente_Id = :id", nativeQuery = true)
    ResponseClienteProjection buscarProyeccionPorId(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE clientes SET cedula = :cedula, nombre_Completo = :nombre_Completo, correo = :correo, telefono = :telefono, clave = :clave, rol = :rol, imagen_url = :imagen_url WHERE cliente_Id = :id", nativeQuery = true)
    void actualizarCliente(
            @Param("id") UUID id,
            @Param("cedula") String cedula,
            @Param("nombre_Completo") String nombre_Completo,
            @Param("correo") String correo,
            @Param("telefono") String telefono,
            @Param("clave") String clave,
            @Param("rol") String rol,
            @Param("imagen_url") String imagen_url);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM clientes WHERE cliente_Id = :id", nativeQuery = true)
    void eliminarCliente(@Param("id") UUID id);
}
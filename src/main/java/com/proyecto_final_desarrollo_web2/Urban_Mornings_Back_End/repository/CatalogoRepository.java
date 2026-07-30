package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.ResponseCatalogoProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {
    List<Catalogo> findAllByActivoTrue();


    @Transactional
    @Query(value = "EXEC casp_RegistroCatalogo :nombre, :descripcion, :precio, :tipo, :categoria_Desayuno,:imagen_url,:activo", nativeQuery = true)
    ResponseCatalogoProjection registrarCatalogo(
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion,
            @Param("precio") BigDecimal precio,
            @Param("tipo") String tipo,
            @Param("categoria_Desayuno") String categoria_Desayuno,
            @Param("imagen_url") String imagen_url,
            @Param("activo")  boolean activo
            );

    @Query(value = "EXEC casp_ListarTodosCatalogos", nativeQuery = true)
    List<ResponseCatalogoProjection> listarTodos();

    @Modifying
    @Transactional
    @Query(value = "UPDATE catalogo SET nombre = :nombre, descripcion = :descripcion, precio = :precio, tipo = :tipo, categoria_Desayuno = :categoria_Desayuno, imagen_url = :imagen_url, activo = :activo WHERE id = :id", nativeQuery = true)
    void actualizarCatalogo(
            @Param("id") Integer id,
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion,
            @Param("precio") BigDecimal precio,
            @Param("tipo") String tipo,
            @Param("categoria_Desayuno") String categoria_Desayuno,
            @Param("imagen_url") String imagen_url,
            @Param("activo") boolean activo
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM catalogo WHERE id = :id", nativeQuery = true)
    void eliminarCatalogo(@Param("id") Integer id);
}

package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.estadisticas.Top5MasVendidoProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface Detalle_PedidoRepository extends JpaRepository<Detalle_Pedido, UUID> {

    @Modifying
    @Transactional
    @Query(value = "EXEC csp_RegistroDetallePedido :detalle_Id, :pedido_Id, :catalogo_id, :cantidad, :precio_Unitario, :sub_Total", nativeQuery = true)
    void registrarDetallePedido(
            @Param("detalle_Id") UUID detalle_Id,
            @Param("pedido_Id") UUID pedido_Id,
            @Param("catalogo_id") int catalogo_id,
            @Param("cantidad") int cantidad,
            @Param("precio_Unitario") BigDecimal precio_Unitario,
            @Param("sub_Total") BigDecimal sub_Total
    );

    @Query(value = "SELECT * FROM detalle_pedido", nativeQuery = true)
    List<Detalle_Pedido> listarTodos();

    @Modifying
    @Transactional
    @Query(value = "UPDATE detalle_pedido SET pedido_Id = :pedido_Id, catalogo_id = :catalogo_id, cantidad = :cantidad, precio_Unitario = :precio_Unitario, sub_Total = :sub_Total WHERE detalle_Id = :detalle_Id", nativeQuery = true)
    void actualizarDetallePedido(
            @Param("detalle_Id") UUID detalle_Id,
            @Param("pedido_Id") UUID pedido_Id,
            @Param("catalogo_id") int catalogo_id,
            @Param("cantidad") int cantidad,
            @Param("precio_Unitario") BigDecimal precio_Unitario,
            @Param("sub_Total") BigDecimal sub_Total
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM detalle_pedido WHERE detalle_Id = :detalle_Id", nativeQuery = true)
    void eliminarDetallePedido(@Param("detalle_Id") UUID detalle_Id);

    boolean existsByDetalleId(UUID detalle_Id);

    @Transactional
    @Query(value = "EXEC R__estsp_Top5Productos", nativeQuery = true)
    List<Top5MasVendidoProjection> top5MasVendido();

}
package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Tipo_Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Metodo_Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Estado_Actual_Pedido;
import org.springframework.beans.factory.annotation.Value;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface PedidoResponseProjection {

    @Value("#{target.pedido.pedidoId}")
    UUID getId();

    @Value("#{target.pedido.codigoRetiro}")
    Long getWithdrawalCode();

    @Value("#{target.pedido.estado_Actual}")
    Estado_Actual_Pedido getStatus();

    @Value("#{target.monto_Pagado}")
    BigDecimal getTotal();

    @Value("#{target.metodo_Pago}")
    Metodo_Pago getPaymentMethod();

    @Value("#{target.pedido.dedicatoria}")
    String getDedication();

    @Value("#{target.pedido.fecha_Creacion}")
    LocalDateTime getCreatedAt();

    @Value("#{target.pedido.fecha_Hora_Retiro?.toLocalDate()}")
    LocalDate getPickupDate();
    @Value("#{target.pedido.fecha_Hora_Retiro?.toLocalTime()}")
    LocalTime getPickupTime();

    // Enrutamos la lista de detalles hacia la sub-interfaz
    @Value("#{target.pedido.detallesPedido}")
    List<ItemsResponsePedidoProjection> getItems();
}

interface ItemsResponsePedidoProjection {

    @Value("#{target.cantidad}")
    Integer getQuantity();

    @Value("#{target.precio_Unitario}")
    BigDecimal getPrecio();

    @Value("#{target.catalogo}")
    ProductoResponsePedidoProjection getProduct();
}

interface ProductoResponsePedidoProjection {

    @Value("#{target.catalogoId}") // Asegúrate de que este nombre coincida con tu variable en Catalogo.java
    Integer getCatalogo_id();

    @Value("#{target.nombre}")
    String getNombre();



    @Value("#{target.tipo}")
    Tipo_Catalogo getTipo();

    @Value("#{target.imagen_Url}")
    String getImagen_Url();
}
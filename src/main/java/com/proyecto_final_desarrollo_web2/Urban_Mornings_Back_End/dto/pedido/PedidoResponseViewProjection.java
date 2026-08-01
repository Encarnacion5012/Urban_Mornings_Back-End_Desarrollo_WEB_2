package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Tipo_Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Metodo_Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Estado_Actual_Pedido;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface PedidoResponseViewProjection {
    @Value("#{target.pedido.pedidoId}")
    UUID getId();

    @Value("#{target.pedido.codigoRetiro}")
    Long getWithdrawalCode();
    @Value("#{target.pedido.cliente.nombreCompleto}")
    String getCustomer();

    @Value("#{target.pedido.cliente.telefono}")
   String getCustomerPhone();

    @Value("#{target.pedido.estado_Actual}")
   Estado_Actual_Pedido getStatus();
    /** Lista de productos del pedido con nombre, cantidad y precio unitario */
    @Value("#{target.pedido.detallesPedido}")
    List<ItemResponseViewProjection> getItems();
    /** Hora de retiro programada */
    @Value("#{target.pedido.fecha_Hora_Retiro.toLocalTime()}")
    LocalTime getTime();
    /** Fecha de retiro programada */
    @Value("#{target.pedido.fecha_Hora_Retiro.toLocalDate()}")
    LocalDate getDate();
    @Value("#{target.pedido.total_Pedido}")
    BigDecimal getTotal();

    @Value("#{target.pedido.dedicatoria}")
    String getDedication();

    @Value("#{target.metodo_Pago}")
    Metodo_Pago getPaymentMethod();
}

interface ItemResponseViewProjection  {
    @Value("#{target.cantidad}")
    int getQuantity();
    @Value("#{target.precio_Unitario}")
    BigDecimal getPrecio();
    @Value("#{target.catalogo}")
    ProductoResponseViewProjection getProduct();
}

interface ProductoResponseViewProjection {
    @Value("#{target.catalogoId}")
    Integer getCatalogo_id();
    @Value("#{target.nombre}")
    String getNombre();
    @Value("#{target.tipo}")
    Tipo_Catalogo getTipo();
    @Value("#{target.imagen_Url}")
    String getImagen_Url();

}



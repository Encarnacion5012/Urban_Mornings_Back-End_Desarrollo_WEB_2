package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Tipo_Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Metodo_Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Estado_Actual_Pedido;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PedidoResponseProjection {
    UUID getId();

    Long getWithdrawalCode();
    Estado_Actual_Pedido getStatus();
    Metodo_Pago getPaymentMethod();
    String getDedication();
    List<ItemsResponsePedidoProjection> getItems();
    LocalDateTime getCreatedAt();

}


 interface ItemsResponsePedidoProjection{

    Integer getQuantity();

    ProductoResponsePedidoProjection getProduct();
}

interface ProductoResponsePedidoProjection{

    Integer getCatalogo_id();

    String getNombre();
    BigDecimal getPrecio();
    Tipo_Catalogo getTipo();
    String getImagen_Url();
}

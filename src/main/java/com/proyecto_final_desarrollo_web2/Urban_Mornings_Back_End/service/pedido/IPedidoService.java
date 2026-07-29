package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;

public interface IPedidoService {
    Pedido registroPedido(RegistroPedidoDTO registroPedidoDTO);
}

package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.detalle_pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido.RegistroDetallePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import java.util.List;
import java.util.UUID;

public interface IDetalle_PedidoService {

    Detalle_Pedido registroDetallePedido(RegistroDetallePedidoDTO detallePedidoDTO);
    List<Detalle_Pedido> listarTodos();
    Detalle_Pedido actualizarDetallePedido(UUID detalleId, RegistroDetallePedidoDTO detallePedidoDTO);
    void eliminarDetallePedido(UUID detalleId);

}
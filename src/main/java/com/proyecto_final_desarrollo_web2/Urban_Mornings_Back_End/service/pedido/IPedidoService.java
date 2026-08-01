package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ActualizarEstadoPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseViewProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;

import java.util.List;
import java.util.UUID;

public interface IPedidoService {
    PedidoResponseProjection registroPedido(RegistroPedidoDTO registroPedidoDTO);

    public List<PedidoResponseProjection> listarPedidosPorcliente(UUID id_cliente);

    Pedido actualizarPedido(UUID id, RegistroPedidoDTO registroPedidoDTO);

    void eliminarPedido(UUID id);

    public List<PedidoResponseProjection> listarPedidos();
    public List<PedidoResponseViewProjection> listarPedidosParaViews();
    public void actulizarEstadoPedido(UUID idPedido , ActualizarEstadoPedidoDTO nuevoEstadoPedidoDTO);
    public Integer actulizarEstatoPedido_Retirado(UUID id_pedido, Long codigo_Retiro);

}

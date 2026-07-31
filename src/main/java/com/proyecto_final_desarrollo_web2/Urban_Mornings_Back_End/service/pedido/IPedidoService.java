package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;

import java.util.List;
import java.util.UUID;

public interface IPedidoService {
    PedidoResponseProjection registroPedido(RegistroPedidoDTO registroPedidoDTO);

    public List<PedidoResponseProjection> listarPedidosPorcliente(UUID id_cliente);

    Pedido actualizarPedido(UUID id, RegistroPedidoDTO registroPedidoDTO);

    void eliminarPedido(UUID id);
    public void actualizarEstado(UUID idPedido, String nuevoEstado);
    public void validarRetiroPedido(UUID idPedido, String codigoIngresado);
    public List<PedidoResponseProjection> listarTodosLosPedidos();


}

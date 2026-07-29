package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import java.util.List;
import java.util.UUID;

public interface IPedidoService {
    Pedido registroPedido(RegistroPedidoDTO registroPedidoDTO);
    List<Pedido> listarTodos();
    Pedido actualizarPedido(UUID id, RegistroPedidoDTO registroPedidoDTO);
    void eliminarPedido(UUID id);
}

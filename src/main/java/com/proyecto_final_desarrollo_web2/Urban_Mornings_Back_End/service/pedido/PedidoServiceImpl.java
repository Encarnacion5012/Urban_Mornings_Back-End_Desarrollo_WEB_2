package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapperService mapperService;

    @Override
    @Transactional
    public Pedido registroPedido(RegistroPedidoDTO registroPedidoDTO) {

        Pedido nuevoPedido = mapperService.registroDTOToEntity(registroPedidoDTO);
        
        if (nuevoPedido.getPedido_Id() == null) {
            nuevoPedido.setPedido_Id(UUID.randomUUID());
        }

        pedidoRepository.registrarPedido(
                nuevoPedido.getPedido_Id(),
                nuevoPedido.getCliente().getClienteId(), 
                nuevoPedido.getFecha_Hora_Retiro(),
                nuevoPedido.getCodigo_Retiro(),
                nuevoPedido.getEstado_Actual().name(),
                nuevoPedido.getTotal_Pedido(),
                nuevoPedido.getFecha_Creacion()
        );

        return nuevoPedido;
    }

    @Override
    @Transactional
    public List<Pedido> listarTodos() {
        return pedidoRepository.listarTodos();
    }

    @Override
    @Transactional
    public Pedido actualizarPedido(UUID id, RegistroPedidoDTO registroPedidoDTO) {
        
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id));

        Pedido datosNuevos = mapperService.registroDTOToEntity(registroPedidoDTO);

        // Llamamos a tu consulta SQL nativa en lugar de usar .save()
        pedidoRepository.actualizarPedido(
                pedidoExistente.getPedido_Id(),
                datosNuevos.getCliente().getClienteId(),
                datosNuevos.getFecha_Hora_Retiro(),
                datosNuevos.getCodigo_Retiro(),
                datosNuevos.getEstado_Actual().name(), // .name() porque es Enum
                datosNuevos.getTotal_Pedido(),
                pedidoExistente.getFecha_Creacion() // Mantenemos la fecha original
        );
        
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarPedido(UUID id) {
        
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id));

        pedidoRepository.eliminarPedido(pedidoExistente.getPedido_Id());
    }
}
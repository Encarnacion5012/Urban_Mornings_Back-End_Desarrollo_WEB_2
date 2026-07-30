package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.detalle_pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.detalle_pedido.RegistroDetallePedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.Detalle_PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.Detalle_PedidoRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Detalle_PedidoServiceImpl implements IDetalle_PedidoService {

    private final Detalle_PedidoRepository detallePedidoRepository;
    private final Detalle_PedidoMapperService mapperService;

    @Override
    @Transactional
    public Detalle_Pedido registroDetallePedido(RegistroDetallePedidoDTO detallePedidoDTO) {
        
        Detalle_Pedido detalle = mapperService.registroDTOToEntity(detallePedidoDTO);
        
        if (detalle.getDetalleId() == null) {
            detalle.setDetalleId(UUID.randomUUID());
        }

        detallePedidoRepository.registrarDetallePedido(
                detalle.getDetalleId(),
                detalle.getPedido().getPedidoId(),
                detalle.getCatalogo().getCatalogo_id(),
                detalle.getCantidad(),
                detalle.getPrecio_Unitario(),
                detalle.getSub_Total()
        );

        return detalle;
    }

    @Override
    @Transactional
    public List<Detalle_Pedido> listarTodos() {
        return detallePedidoRepository.listarTodos();
    }

    @Override
    @Transactional
    public Detalle_Pedido actualizarDetallePedido(UUID detalleId, RegistroDetallePedidoDTO detallePedidoDTO) {
        
        Detalle_Pedido detalleExistente = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con el ID: " + detalleId));
        
        Detalle_Pedido datosNuevos = mapperService.registroDTOToEntity(detallePedidoDTO);

        detallePedidoRepository.actualizarDetallePedido(
                detalleExistente.getDetalleId(),
                datosNuevos.getPedido().getPedidoId(),
                datosNuevos.getCatalogo().getCatalogo_id(),
                datosNuevos.getCantidad(),
                datosNuevos.getPrecio_Unitario(),
                datosNuevos.getSub_Total()
        );

        return detallePedidoRepository.findById(detalleId).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarDetallePedido(UUID detalleId) {
        
        Detalle_Pedido detalleExistente = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con el ID: " + detalleId));

        detallePedidoRepository.eliminarDetallePedido(detalleExistente.getDetalleId());
    }
}
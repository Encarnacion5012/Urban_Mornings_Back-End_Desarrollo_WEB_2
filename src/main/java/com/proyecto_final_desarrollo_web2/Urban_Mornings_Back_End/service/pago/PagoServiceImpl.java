package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pago;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago.RegistroPagoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PagoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PagoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements IPagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapperService pagoMapper;

    @Override
    @Transactional
    public Pago registroPago(RegistroPagoDTO registroDto) {
        
        Pago pago = pagoMapper.registroDTOToEntity(registroDto);

        if (pago.getPago_Id() == null) {
            pago.setPago_Id(UUID.randomUUID());
        }
        
        if (pago.getFecha_Pago() == null) {
            pago.setFecha_Pago(LocalDateTime.now());
        }

        pagoRepository.registrarPagoSP(
                pago.getPago_Id(),
                pago.getMonto_Pagado(),
                pago.getPedido().getPedido_Id(), 
                pago.getMetodo_Pago().name(),
                pago.getTransaccion_Id(),
                pago.getFecha_Pago()
        );

        return pago;
    }

    @Override
    @Transactional
    public List<Pago> listarTodos() {
        return pagoRepository.listarTodos();
    }

    @Override
    @Transactional
    public Pago actualizarPago(UUID pagoId, RegistroPagoDTO registroDto) {
        
        Pago pagoExistente = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con el ID: " + pagoId));

        Pago datosNuevos = pagoMapper.registroDTOToEntity(registroDto);

        pagoRepository.actualizarPago(
                pagoExistente.getPago_Id(),
                datosNuevos.getMonto_Pagado(),
                datosNuevos.getPedido().getPedido_Id(),
                datosNuevos.getMetodo_Pago().name(), // .name() porque es un Enum
                datosNuevos.getTransaccion_Id(),
                datosNuevos.getFecha_Pago() != null ? datosNuevos.getFecha_Pago() : pagoExistente.getFecha_Pago()
        );

        return pagoRepository.findById(pagoId).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarPago(UUID pagoId) {
        
        Pago pagoExistente = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con el ID: " + pagoId));

        pagoRepository.eliminarPago(pagoExistente.getPago_Id());
    }
}
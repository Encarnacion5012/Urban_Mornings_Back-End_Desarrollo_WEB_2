package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pago;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pago.RegistroPagoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;

import java.util.List;
import java.util.UUID;

public interface IPagoService {

    Pago registroPago(RegistroPagoDTO registroDto);

    List<Pago> listarTodos();

    Pago actualizarPago(UUID pagoId, RegistroPagoDTO registroDto);

    void eliminarPago(UUID pagoId);
}
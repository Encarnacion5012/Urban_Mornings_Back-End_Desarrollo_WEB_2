package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.email;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails.SolicitudRecuperacionDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    public void sendEmail( SolicitudRecuperacionDTO solicitudRecuperacionDTO) throws MessagingException;
    public boolean validarCodigoSinConsumir(EmailServiceImpl.DatosValidacion datosValidacion);
    public void actulizarClave(EmailServiceImpl.DatosNuevaCLave datosNuevaCLave);
    public boolean validarYConsumirCodigo(EmailServiceImpl.DatosValidacion datosValidacion);
}

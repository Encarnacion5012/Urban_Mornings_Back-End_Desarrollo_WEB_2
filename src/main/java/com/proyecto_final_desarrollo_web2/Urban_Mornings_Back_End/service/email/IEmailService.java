package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.email;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails.SolicitudRecuperacionDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails.SolicitudVerificacionEmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    public void sendEmailRecuperacion(SolicitudRecuperacionDTO solicitudRecuperacionDTO) throws MessagingException;
    public boolean validarCodigoSinConsumir(EmailServiceImpl.DatosValidacion datosValidacion);
    public void actulizarClave(EmailServiceImpl.DatosNuevaCLave datosNuevaCLave);
    public boolean validarYConsumirCodigo(EmailServiceImpl.DatosValidacion datosValidacion);
    public boolean verificarEmail(EmailServiceImpl.DatosValidacion datosValidacion);
    public void sendEmailVerificacion(SolicitudVerificacionEmailDTO solicitudVerificacionEmailDTO);
    public void sendMensajeBienvenida(String nombreCLiente, String correo);
}

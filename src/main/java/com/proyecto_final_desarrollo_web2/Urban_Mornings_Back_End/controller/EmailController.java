package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails.SolicitudRecuperacionDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.email.EmailServiceImpl;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.email.IEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final IEmailService emailService;

    @PostMapping("/solicitudRecuperacion")
    public ResponseEntity<?> solicitudRecuperacion(@RequestBody SolicitudRecuperacionDTO solicitudRecuperacionDTO) throws MessagingException {
        emailService.sendEmail(solicitudRecuperacionDTO);

        return ResponseEntity.ok("Enviado COrrectamente");
    }

    @PostMapping("/Verificar-codigo")
    public ResponseEntity<?> validarCOdigo(@RequestBody EmailServiceImpl.DatosValidacion datosValidacion) {
        boolean codigoVerficado = emailService.validarCodigoSinConsumir(datosValidacion);

        if (codigoVerficado) {
            return ResponseEntity.ok("Validado COrrectamente");
        }
        return ResponseEntity.badRequest().body("Codigo ivalido o expirado");

    }

    @PostMapping("actualizarCLave")
    public ResponseEntity<?> actualizarCLave(@RequestBody EmailServiceImpl.DatosNuevaCLave datosNuevaClave) {
        try {
            emailService.actulizarClave(datosNuevaClave);
            return ResponseEntity.ok("Actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

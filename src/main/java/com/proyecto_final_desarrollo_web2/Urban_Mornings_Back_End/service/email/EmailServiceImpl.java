package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.email;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.emails.SolicitudRecuperacionDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.security.IAppUser;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.UsuarioRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public record DatosCodigo(Long codigo, LocalDateTime expiracion){}
    public record DatosValidacion(String email, Long codigo){}
    public record DatosNuevaCLave(String email, Long codigo, String claveNueva){}

    private final Map<String, DatosCodigo> mapaDeCodigos = new ConcurrentHashMap<>();

    @Override
    public void sendEmail(SolicitudRecuperacionDTO solicitudRecuperacionDTO) throws MessagingException {

        IAppUser userDetails = (IAppUser) usuarioRepository.findByCorreo(solicitudRecuperacionDTO.correo())
                .orElseGet(() -> clienteRepository.findByCorreo(solicitudRecuperacionDTO.correo())
                        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));

        Long codigoVerificacion = generarCodigorRecuperacion();
        mapaDeCodigos.put(userDetails.getCorreo(), new DatosCodigo(codigoVerificacion, LocalDateTime.now().plusMinutes(15)));

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(userDetails.getCorreo());
            helper.setSubject("Código de recuperación de contraseña");

            Context context = new Context();
            context.setVariable("nombre", userDetails.getNombre_Completo());
            context.setVariable("codigoVerificacion", codigoVerificacion);

            String contentHtml = templateEngine.process("emailRecuperacion", context);

            helper.setText(contentHtml, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el mensaje: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean validarCodigoSinConsumir(DatosValidacion datosValidacion) {
        DatosCodigo datos = mapaDeCodigos.get(datosValidacion.email());

        if (datos == null) return false;


        return datos.codigo().equals(datosValidacion.codigo()) && LocalDateTime.now().isBefore(datos.expiracion());




    }

    @Override
    public boolean validarYConsumirCodigo(DatosValidacion datosValidacion) {
        if (validarCodigoSinConsumir(datosValidacion)) {
            mapaDeCodigos.remove(datosValidacion.email());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void actulizarClave(DatosNuevaCLave datosNuevaCLave) {
        // Aquí se valida y se borra de la memoria en un solo paso
        boolean esCodigoValido = validarYConsumirCodigo(new DatosValidacion(datosNuevaCLave.email(), datosNuevaCLave.codigo()));
        if (!esCodigoValido) {
            throw new IllegalArgumentException("El código introducido es inválido o ha expirado.");
        }

        UserDetails usuario = usuarioRepository.findByCorreo(datosNuevaCLave.email())
                .orElseGet(() -> clienteRepository.findByCorreo(datosNuevaCLave.email())
                        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));

        if (usuario instanceof Usuario u) {
            u.setClave(passwordEncoder.encode(datosNuevaCLave.claveNueva()));
        } else if (usuario instanceof Cliente c) {
            c.setClave(passwordEncoder.encode(datosNuevaCLave.claveNueva()));
        }
    }

    private Long generarCodigorRecuperacion() {
        long min = 100_000L;
        long max = 1000_000L;
        return ThreadLocalRandom.current().nextLong(min, max);
    }
}
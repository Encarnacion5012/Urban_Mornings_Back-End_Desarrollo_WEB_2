package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ActuializarClientesDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.exeption.exeptions.EmailAlreadyExistExeption;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.ClienteMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente_Usuario_Roles;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.email.IEmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClientesService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapperService clienteMapper;
    private final IEmailService emailService;

    @Override
    @Transactional
    public ResponseClienteProjection registroClientes(RegistroClienteDTO registroDto) {

        Cliente cliente = clienteMapper.registerDtoTOEntity(registroDto);

        if (clienteRepository.existsByCorreo(cliente.getCorreo())) {
            throw new EmailAlreadyExistExeption("Este correo ya está registrado");
        }

        cliente.setClienteId(generarClienteId());


        clienteRepository.registrarCLiente(
                cliente.getClienteId(),
                cliente.getCedula(),
                cliente.getNombreCompleto(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getClave(),
                cliente.getRol().name(),
                cliente.getImagen_url(),
                cliente.getFecha_Registro());

        emailService.sendMensajeBienvenida(cliente.getNombreCompleto(), cliente.getCorreo());

        // Buscamos el cliente guardado y lo devolvemos como proyección (ya no da error)
        return clienteRepository.buscarProyeccionPorId(cliente.getClienteId());
    }

    @Override
    @Transactional
    public List<ResponseClienteProjection> listarTodos() {
        // Devuelve la lista directa desde el SP, mapeada a proyecciones
        return clienteRepository.listarClientes();
    }

    @Override
    @Transactional
    public Cliente actualizarCliente(UUID id, ActuializarClientesDTO actualizacionDto) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));

         clienteMapper.actualizarClientes(actualizacionDto, clienteExistente);



        return clienteExistente;
    }

    @Override
    @Transactional
    public void eliminarCliente(UUID id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));

        clienteRepository.eliminarCliente(clienteExistente.getClienteId());
    }

    private UUID generarClienteId() {
        UUID id_generado = UUID.randomUUID();
        if (clienteRepository.existsById(id_generado)) {
            return generarClienteId();
        } else {
            return id_generado;
        }
    }
}
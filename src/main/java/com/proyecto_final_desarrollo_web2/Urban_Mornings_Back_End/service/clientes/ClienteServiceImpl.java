package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.exeption.exeptions.EmailAlreadyExistExeption;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.ClienteMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClientesService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapperService clienteMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseClienteProjection registroClientes(RegistroClienteDTO registroDto){


        Cliente cliente = clienteMapper.registerDtoTOEntity(registroDto);

        if(clienteRepository.existsByCorreo(cliente.getCorreo())){
            throw new EmailAlreadyExistExeption("Este correo ya esta registrado");
        }

        cliente.setClienteId(generarClienteId());

        return clienteRepository.registrarCLiente(
                cliente.getClienteId(),
                cliente.getCedula(),
                cliente.getNombre_Completo(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getClave(),
                cliente.getRol().name(),
                cliente.getImagen_url(),
                cliente.getFecha_Registro()
                );
    }


    @Override
    public List<ResponseClienteProjection> listarTodos() {
        return clienteRepository.listarClientes();
    }

    @Override
    @Transactional
    public Cliente actualizarCliente(UUID id, RegistroClienteDTO registroDto) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));

        Cliente datosNuevos = clienteMapper.registerDtoTOEntity(registroDto);

        // También encriptamos si actualizan la clave
        String claveEncriptada = passwordEncoder.encode(datosNuevos.getClave());

        clienteRepository.actualizarCliente(
                clienteExistente.getClienteId(),
                datosNuevos.getCedula(),
                datosNuevos.getNombre_Completo(),
                datosNuevos.getCorreo(),
                datosNuevos.getTelefono(),
                claveEncriptada, // Usamos la clave encriptada
                "USER", // Mantenemos el rol
                datosNuevos.getImagen_url()
        );

        return clienteRepository.findById(id).orElse(null);
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

        if (clienteRepository.existsByClienteId(id_generado)) {
            return generarClienteId();
        } else {
            return id_generado;
        }

    }
}

package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.exeption.exeptions.EmailAlreadyExistExeption;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.ClienteMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClientesService {

    private final ClienteRepository clienteRepository;
    private  final ClienteMapperService clienteMapper;


    @Override
    @Transactional
    public UUID registroClientes(RegistroClienteDTO registroDto){


        Cliente cliente = clienteMapper.registerDtoTOEntity(registroDto);

        if(clienteRepository.existsByCorreo(cliente.getCorreo())){
            throw new EmailAlreadyExistExeption("Este correo ya esta registrado");
        }

        cliente.setClienteId(generarClienteId());

        clienteRepository.registrarCLiente(
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

        return cliente.getClienteId();


    }


    public List<Cliente> listarClientesProcedue(){
        return clienteRepository.listarClientes();
    }

    //Generamos el uuid y compbromaos si existe en la base datos y si ese fuese el caso disparamos la fncion de nuevo
    // hasta encontrar alguno que no este registrado

    private UUID generarClienteId(){

        UUID id_generado = UUID.randomUUID();

        if (clienteRepository.existsByClienteId(id_generado)){
            return   generarClienteId();
        }
        else {
            return  id_generado;
        }

    }
}

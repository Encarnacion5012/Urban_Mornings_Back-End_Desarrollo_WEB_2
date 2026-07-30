package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import java.util.List;
import java.util.UUID;

public interface IClientesService {
    public ResponseClienteProjection registroClientes(RegistroClienteDTO registroDto);


    public List<ResponseClienteProjection>  listarTodos();

    public Cliente actualizarCliente(UUID id, RegistroClienteDTO registroDto);

    public void eliminarCliente(UUID id);

}

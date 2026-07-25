package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;

import java.util.UUID;

public interface IClientesService {
    public UUID registroClientes(RegistroClienteDTO registroDto);

}

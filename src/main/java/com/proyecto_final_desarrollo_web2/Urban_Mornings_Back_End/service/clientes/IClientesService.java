package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteProjection;

import java.util.List;

public interface IClientesService {
    public ResponseClienteProjection registroClientes(RegistroClienteDTO registroDto);
    public List<ResponseClienteProjection> listarClientesProcedue();
}

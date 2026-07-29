package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.catalogo;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.RegistroCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;

import java.util.List;

public interface ICatalogoService {
    public Catalogo registroCatalgo(RegistroCatalogoDTO catalogoDTO);
    public List<Catalogo> listarTodos();
    public Catalogo actualizarCatalogo(Integer id, RegistroCatalogoDTO catalogoDTO);
    public void eliminarCatalogo(Integer id);
}

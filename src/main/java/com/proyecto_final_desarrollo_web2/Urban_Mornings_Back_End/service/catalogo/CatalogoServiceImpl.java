package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.catalogo;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.RegistroCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.CatalogoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.CatalogoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements ICatalogoService {
    private final CatalogoRepository catalogoRepository;
    private final CatalogoMapperService catalogoMapperService;

    @Transactional
    @Override
    public Catalogo registroCatalgo(RegistroCatalogoDTO catalogoDTO) {
        Catalogo catalogo = catalogoMapperService.resgistroDTOToEntity(catalogoDTO);

        return  catalogoRepository.registrarCatalogo(
                catalogo.getNombre(),
                catalogo.getDescripcion(),
                catalogo.getPrecio(),
                catalogo.getTipo().name(),
                catalogo.getCategoria_Desayuno().name(),
                catalogo.getImagen_Url(),
                catalogo.getActivo()
        );
    }

    @Override
    public List<Catalogo>listarTodos(){
        return catalogoRepository.listarTodos();
    }
}

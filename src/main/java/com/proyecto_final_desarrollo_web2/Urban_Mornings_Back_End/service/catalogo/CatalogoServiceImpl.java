package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.catalogo;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.RegistroCatalogoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.catalogo.ResponseCatalogoProjection;
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
    public ResponseCatalogoProjection registroCatalgo(RegistroCatalogoDTO catalogoDTO) {
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
    public List<ResponseCatalogoProjection>listarTodos(){
        return catalogoRepository.listarTodos();
    }

    @Transactional
    @Override
    public Catalogo actualizarCatalogo(Integer id, RegistroCatalogoDTO catalogoDTO) {
        Catalogo catalogoExistente = catalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado con el ID: " + id));

        Catalogo datosNuevos = catalogoMapperService.resgistroDTOToEntity(catalogoDTO);

        catalogoExistente.setNombre(datosNuevos.getNombre());
        catalogoExistente.setDescripcion(datosNuevos.getDescripcion());
        catalogoExistente.setPrecio(datosNuevos.getPrecio());
        catalogoExistente.setTipo(datosNuevos.getTipo());
        catalogoExistente.setCategoria_Desayuno(datosNuevos.getCategoria_Desayuno());
        catalogoExistente.setImagen_Url(datosNuevos.getImagen_Url());
        catalogoExistente.setActivo(datosNuevos.getActivo());

        return catalogoRepository.save(catalogoExistente);
    }

    @Transactional
    @Override
    public void eliminarCatalogo(Integer id) {
        Catalogo catalogoExistente = catalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado con el ID: " + id));

        catalogoRepository.delete(catalogoExistente);
    }
}
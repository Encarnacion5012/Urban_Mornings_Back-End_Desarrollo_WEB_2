package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapperService usuarioMapperService;


    @Transactional
    @Override
    public Usuario registroUsuario(RegistroUsuarioDTO dto) {
        Usuario usuario = usuarioMapperService.registroDtoToEntity(dto);

        usuario.setIdUsuario(generar_UUid_Usuario());

       return usuarioRepository.registarUsuario(
                usuario.getIdUsuario(),
                usuario.getCedula(),
                usuario.getNombre_Completo(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getClave(),
                usuario.getRol().name(),
                usuario.getImagen_url(),
                usuario.isActivo()
        );


    }


    private UUID generar_UUid_Usuario() {
        UUID uuid_Generado = UUID.randomUUID();

        if (usuarioRepository.existsByIdUsuario(uuid_Generado)) {
            return generar_UUid_Usuario();
        }
        else {
            return uuid_Generado;
        }

    }





}

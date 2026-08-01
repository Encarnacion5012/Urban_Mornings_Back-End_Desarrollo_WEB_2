package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ActualizarUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapperService usuarioMapperService;

    @Transactional
    @Override
    public ResponseUsuarioProjection registroUsuario(RegistroUsuarioDTO dto) {
        Usuario usuario = usuarioMapperService.registroDTOToEntity(dto);

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
                usuario.isActivo());



    }

    public List<ResponseUsuarioProjection> listarUsuarios() {
        return usuarioRepository.listarUsuarios();


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



    @Override
    @Transactional
    public Integer eliminarUsuario(UUID id_usuario) {

        Usuario usuarioExistente = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id_usuario));

        return usuarioRepository.eliminarUsuario(usuarioExistente.getIdUsuario());


    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(UUID id_usuario, ActualizarUsuarioDTO dto) {

        Usuario usuarioExistente = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el ID: " + id_usuario));

        usuarioMapperService.actualizarUsuarioDesdeDtoActualizacion(dto, usuarioExistente);



        return usuarioExistente;
    }
}
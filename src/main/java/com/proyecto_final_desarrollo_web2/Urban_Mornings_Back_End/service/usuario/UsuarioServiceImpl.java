package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapperService usuarioMapper;
    private final PasswordEncoder passwordEncoder; // Se inyecta para encriptar claves

    @Transactional
    @Override
    public ResponseUsuarioProjection registroUsuario(RegistroUsuarioDTO dto) {
        Usuario usuario = usuarioMapper.registroDTOToEntity(dto);

        usuario.setIdUsuario(generar_UUid_Usuario());

        // 1. Encriptamos la clave plana que llegó del DTO
        String claveEncriptada = passwordEncoder.encode(usuario.getClave());
        usuario.setClave(claveEncriptada);

        // 2. Activamos la cuenta para que Spring Security lo deje entrar
        usuario.setActivo(true);

        return usuarioRepository.registarUsuario(
                usuario.getIdUsuario(),
                usuario.getCedula(),
                usuario.getNombre_Completo(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getClave(), // ¡Ahora sí pasará el hash de Bcrypt!
                usuario.getRol().name(),
                usuario.getImagen_url(),
                usuario.isActivo()); // ¡Ahora sí pasará como true (1)!
    }

    public List<ResponseUsuarioProjection> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    private UUID generar_UUid_Usuario() {
        UUID uuid_Generado = UUID.randomUUID();

        if (usuarioRepository.existsByIdUsuario(uuid_Generado)) {
            return generar_UUid_Usuario();
        } else {
            return uuid_Generado;
        }
    }

    @Override
    @Transactional
    public void eliminarUsuario(UUID id_usuario) {

        Usuario usuarioExistente = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id_usuario));

        usuarioRepository.eliminarUsuario(usuarioExistente.getIdUsuario());
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(UUID id_usuario, RegistroUsuarioDTO dto) {

        Usuario usuarioExistente = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id_usuario));

        Usuario datosNuevos = usuarioMapper.registroDTOToEntity(dto);

        // Encriptar la nueva clave al actualizar
        String claveEncriptada = passwordEncoder.encode(datosNuevos.getClave());

        usuarioRepository.actualizarUsuario(
                usuarioExistente.getIdUsuario(),
                datosNuevos.getCedula(),
                datosNuevos.getNombre_Completo(),
                datosNuevos.getCorreo(),
                datosNuevos.getTelefono(),
                claveEncriptada, // Actualizar con clave encriptada
                datosNuevos.getRol().name(),
                datosNuevos.getImagen_url(),
                datosNuevos.isActivo());
                
        return datosNuevos;
    }
}
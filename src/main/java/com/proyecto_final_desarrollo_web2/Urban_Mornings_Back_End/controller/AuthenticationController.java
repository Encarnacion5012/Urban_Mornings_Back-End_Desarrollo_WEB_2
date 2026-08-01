package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;

import com.nimbusds.jose.JOSEException;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.LoginDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.tokenJWT.TokenJWTDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.tokenJWT.TokenService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.clientes.IClientesService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.usuario.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager auManager;
    private final TokenService tokenService;
    private final IClientesService clienteService;
    private final IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenJWTDTO> login(@RequestBody @Valid LoginDTO loginDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.clave());
        var authentication = auManager.authenticate(authenticationToken);

        var userDetails = (UserDetails) authentication.getPrincipal();

        String tokenJWT = tokenService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

    @PostMapping("/register/clientes")
    public ResponseEntity<?> registroUsuarios(@RequestBody @Valid RegistroClienteDTO registroDto, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = clienteService.registroClientes(registroDto);
        
        // Aquí ya pusimos el getCliente_Id() correcto de tu Proyección
        URI uri = uriComponentsBuilder.path("/auth/clientes/{id}").buildAndExpand(cliente.getCliente_Id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PostMapping("/register/usuarios")
     @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroDto, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = usuarioService.registroUsuario(registroDto);
        
        // Ojo aquí: puse getId_usuario() asumiendo que así se llama en tu Proyección.
        URI uri = uriComponentsBuilder.path("/auth/usuarios/{id}").buildAndExpand(usuario.getId_usuario()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
}
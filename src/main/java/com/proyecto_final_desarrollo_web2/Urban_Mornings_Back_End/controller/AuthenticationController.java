package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.controller;


import com.nimbusds.jose.JOSEException;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.LoginDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.RegistroClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.clientes.ResponseClienteDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.RegistroUsuarioDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.usuario.ResponseUsuarioDto;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.tokenJWT.TokenJWTDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.tokenJWT.TokenService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.ClienteMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.UsuarioMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
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
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager auManager;
    private final TokenService tokenService;
    private final IClientesService  clienteService;
    private final ClienteMapperService clienteMapperService;
    private final UsuarioMapperService usuarioMapperService;
    private final IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenJWTDTO> login(@RequestBody @Valid LoginDTO loginDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
    var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.clave());
    var authentication =  auManager.authenticate(authenticationToken);

    var userDetails = (UserDetails) authentication.getPrincipal();

    String tokenJWT = tokenService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }



    @PostMapping("/register/clientes")
    public ResponseEntity registroUsuarios(@RequestBody @Valid RegistroClienteDTO registroDto, UriComponentsBuilder uriComponentsBuilder) {
        UUID cliente_id = clienteService.registroClientes(registroDto);
        URI uri = uriComponentsBuilder.path("/auth/clientes/{id}").buildAndExpand(cliente_id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/register/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroDto, UriComponentsBuilder uriComponentsBuilder) {
        UUID usuario_id = usuarioService.registroUsuario(registroDto);

        URI uri = uriComponentsBuilder.path("/auth/usuarios/{id}").buildAndExpand(usuario_id).toUri();

        return ResponseEntity.created(uri).build();

    }



}

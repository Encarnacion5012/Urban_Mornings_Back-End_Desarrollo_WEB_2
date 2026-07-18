package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {


//Tenemos dos tabalas para autenticar, usuraios y clientes, primero buscamos en una
// y si no esta vamos a otra y si no esta finalmente lanzamos una exepcion
//Recordar que para esto en el repository implemtamos optional en ambos userDatils
   return usuarioRepository.findByCorreo(correo)
           .orElseGet(()-> clienteRepository.findByCorreo(correo)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"))
           );
}
}

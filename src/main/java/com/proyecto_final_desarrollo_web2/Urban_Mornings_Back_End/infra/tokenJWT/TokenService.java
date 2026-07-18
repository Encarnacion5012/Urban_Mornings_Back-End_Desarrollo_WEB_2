package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.tokenJWT;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.security.IAppUser;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;

    @Value("${app.jwt.issuer}")
    private String ISSUER; //   Esta es constante por esto esta en mayuscula


    public String generateToken(UserDetails user){


        IAppUser userDates = (IAppUser) user;

//        String username ="";
//        String imagenUrl = "";
//        UUID id_subject = null;
//
//        if (user instanceof Usuario) {
//           Usuario usuario = (Usuario) user;
//            username=usuario.getNombre_Completo();
//            id_subject = usuario.getId_usuario();
//            imagenUrl = usuario.getImagen_url();
//        }
//
//        if (user instanceof Cliente) {
//            Cliente cliente = (Cliente) user;
//            username = cliente.getNombre_Completo();
//            id_subject =cliente.getCliente_Id();
//            imagenUrl = cliente.getImagen_url();
//
//        }


        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .subject(userDates.getUserId().toString())
                .claim("roles", roles)
                .claim("email", user.getUsername())
                .claim("imagenUrl", userDates.getUrl_Imagen())
                .claim("username", ((IAppUser) user).getNombre_Completo())
                .build();


        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }







}




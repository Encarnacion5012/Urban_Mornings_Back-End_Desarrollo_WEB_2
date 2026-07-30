package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.tokenJWT;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.security.IAppUser;
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

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;

    @Value("${app.jwt.issuer}")
    private String ISSUER; //   Esta es constante por esto esta en mayuscula


    public String generateToken(UserDetails user){


        IAppUser userDates = (IAppUser) user;

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




package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Value("classpath:jwtkeys/private_key.pem")
    private Resource privateKeyResource;

    @Value("classpath:jwtkeys/public_key.pem")
    private Resource publicKeyResource;


    @Bean
    public JwtEncoder jwtEncoder() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
       RSAPublicKey publicKey = (RSAPublicKey) loadPublicKey(publicKeyResource);
       RSAPrivateKey privateKey = (RSAPrivateKey) loadPrivateKey(privateKeyResource);

        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

       return new NimbusJwtEncoder(jwks);
    }


    @Bean
    public JwtDecoder jwtDecoder() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKey publicKey = (RSAPublicKey) loadPublicKey(publicKeyResource);
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }



    private RSAPrivateKey loadPrivateKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));

        String privateKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PRIVATE KEY-----","")
                .replace("-----END PRIVATE KEY-----","")
                .replaceAll("\\s","");

        byte[] decodedKey = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
    }

    private RSAPublicKey loadPublicKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = resource.getInputStream().readAllBytes();

        String publicKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PUBLIC KEY-----","")
                .replace("-----END PUBLIC KEY-----","")
                .replaceAll("\\s","");

        byte[] decodedKey = Base64.getDecoder().decode(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
    }
}

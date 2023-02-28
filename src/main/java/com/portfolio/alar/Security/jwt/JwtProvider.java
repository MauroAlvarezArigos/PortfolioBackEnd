package com.portfolio.alar.Security.jwt;

import com.portfolio.alar.Security.Entity.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("MiUltraSecreto")
    private String secret;
    @Value("10000") // Una semana
    private int expiration;

    public String generateToken(Authentication auth) {
        UsuarioPrincipal up = (UsuarioPrincipal) auth.getPrincipal();
        return Jwts.builder().setSubject(up.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expiration * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Hubo un problema en la formacion del Token.");
        } catch (UnsupportedJwtException e) {
            logger.error("El token que se intenta evaluar no es soportado por la aplicacion.");
        } catch (ExpiredJwtException e) {
            logger.error("El token ha expirado porfavor reintente la operacion mas tarde.");
        } catch (IllegalArgumentException e) {
            logger.error("El token ingresado estaba vacio.");
        } catch (SignatureException e) {
            logger.error("La firma del token no es valida, reintente mas tarde.");
        }
        return false;
    }
}

package ru.ixlax.TodoWebApp.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.models.user.UserDetailsImpl;

import java.util.Date;

@Component
@Slf4j
public class JwtCore {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.lifetime}")
    private Integer lifeTime;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifeTime))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public String getEmailFromJwt(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            log.error("Недействительная подпись JWT: {}", e.getMessage());
            // Handle the exception appropriately
            return null; // Or throw a custom exception
        } catch (ExpiredJwtException e) {
            log.error("Срок действия JWT истек: {}", e.getMessage());
            // Handle expired token
            return null;
        } catch (MalformedJwtException e) {
            log.error("Неправильный JWT: {}", e.getMessage());
            return null;
        } catch (UnsupportedJwtException e) {
            log.error("JWT не поддерживается: {}", e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return null;
        }
    }
}

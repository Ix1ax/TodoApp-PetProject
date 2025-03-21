package ru.ixlax.TodoWebApp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.models.user.UserDetailsImpl;

import java.util.Date;

@Component
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
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }
}

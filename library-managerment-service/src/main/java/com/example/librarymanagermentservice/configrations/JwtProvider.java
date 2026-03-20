package com.example.librarymanagermentservice.configrations;

import com.example.librarymanagermentservice.common.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Information about jwt provider.
 */
@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    /**
     * Generate token.
     * @param authentication Authentication.
     * @return String.
     */
    public String generateToken(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = populateAuthorities(authorities);
        return Jwts
                .builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION_TIME))
                .claim(JwtConstant.EMAIL, authentication.getName())
                .claim(JwtConstant.AUTHORITIES, roles)
                .signWith(key)
                .compact();
    }

    /**
     * Get email from jwt token.
     * @param jwt String.
     * @return String.
     */
    public String getEmailFromJwtToken(String jwt) {
        String token = jwt.substring(JwtConstant.BEGIN_INDEX);
        Claims claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return String.valueOf(claims.get(JwtConstant.EMAIL));
    }

    /**
     * Populate authorities.
     * @param authorities Collection<? extends GrantedAuthority>.
     * @return String.
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for (GrantedAuthority authority: authorities) {
            auths.add(authority.getAuthority());
        }

        return String.join(",", auths);
    }
}

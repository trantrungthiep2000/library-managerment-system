package com.example.librarymanagermentservice.configrations;

import com.example.librarymanagermentservice.common.JwtConstant;
import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;
import com.example.librarymanagermentservice.dto.response.ApiErrorResponseDTO;
import com.example.librarymanagermentservice.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

/**
 * Information about jwt validator.
 */
public class JwtValidator extends OncePerRequestFilter {
    private final HandlerExceptionResolver resolver;

    public JwtValidator(HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * Do filter internal.
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     * @param filterChain FilterChain.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/api/v1/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = request.getHeader(JwtConstant.AUTHORIZATION);
        if (jwt != null) {
            String token = jwt.substring(JwtConstant.BEGIN_INDEX);

            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
                String email = String.valueOf(claims.get(JwtConstant.EMAIL));
                String authorities = String.valueOf(claims.get(JwtConstant.AUTHORITIES));
                List<GrantedAuthority> authorityList = AuthorityUtils
                        .commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, null, authorityList
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                ApiErrorResponseDTO errorResponseDTO = new ApiErrorResponseDTO(
                        StatusCodeConstant.UNAUTHORIZED,
                        StatusCodeStringConstant.UNAUTHORIZED,
                        List.of(ex.getMessage())
                );
                resolver.resolveException(request, response, errorResponseDTO, new UnauthorizedException(ex.getMessage()));
            }
        }

        filterChain.doFilter(request, response);
    }
}

package com.example.librarymanagermentservice.configrations;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.JwtConstant;
import com.example.librarymanagermentservice.common.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;
import java.util.Collections;

/**
 * Information about security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final HandlerExceptionResolver resolver;

    public SecurityConfig(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * Security filter chain.
     * @param httpSecurity HttpSecurity.
     * @return SecurityFilterChain.
     * @throws Exception Exception.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(managerment -> managerment
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/admin/**").hasAuthority(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/api/v1/subscription-plans/admin/**").hasAuthority(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtValidator(resolver), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()
                ))
                .build();
    }

    /**
     * Password encoder.
     * @return PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Cors configuration source.
     * @return CorsConfigurationSource.
     */
    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowCredentials(true);
            cfg.setAllowedOrigins(Arrays.asList(
                    Constant.BACKEND_URL,
                    Constant.FRONTEND_URL
            ));
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(Collections.singletonList(JwtConstant.AUTHORIZATION));
            cfg.setMaxAge(Constant.MAX_AGE);
            return cfg;
        };
    }
}

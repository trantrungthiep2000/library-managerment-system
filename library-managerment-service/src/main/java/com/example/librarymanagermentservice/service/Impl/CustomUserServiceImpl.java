package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.message.UserMessageError;
import com.example.librarymanagermentservice.exception.BadRequestException;
import com.example.librarymanagermentservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.librarymanagermentservice.model.User;

import java.util.Collection;
import java.util.Collections;

/**
 * Information about custom user service implement.
 */
@Service
@AllArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Load user by email.
     * @param userName String.
     * @return UserDetails.
     */
    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByEmail(userName).orElse(null);
        if (user == null) {
            throw new BadRequestException(UserMessageError.ACCOUNT_INVALID_CREDENTIALS);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(authority);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities
        );
    }
}

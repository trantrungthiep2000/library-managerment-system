package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.JwtConstant;
import com.example.librarymanagermentservice.common.message.PasswordResetTokenError;
import com.example.librarymanagermentservice.common.message.UserMessageError;
import com.example.librarymanagermentservice.configrations.JwtProvider;
import com.example.librarymanagermentservice.dto.request.LoginRequestDTO;
import com.example.librarymanagermentservice.dto.request.UserRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.AuthResponseDTO;
import com.example.librarymanagermentservice.exception.BadRequestException;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.UserMapper;
import com.example.librarymanagermentservice.model.PasswordResetToken;
import com.example.librarymanagermentservice.repository.PasswordResetTokenRepository;
import com.example.librarymanagermentservice.repository.UserRepository;
import com.example.librarymanagermentservice.service.AuthService;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Information about auth service implements.
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImpl customUserServiceImpl;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;

    /**
     * Login.
     * @param requestDTO LoginRequestDTO.
     * @return AuthResponseDTO.
     */
    @Override
    @Transactional
    public ApiSuccessResponseDTO<AuthResponseDTO> login(LoginRequestDTO requestDTO) {
        Authentication authentication = authenticate(requestDTO.getEmail(), requestDTO.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(requestDTO.getEmail()).orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        // Update last login
        user.setLastLogin(LocalDateTime.now());
        user = userRepository.save(user);
        AuthResponseDTO authResponseDTO = createAuthResponseDTO(token, user);

        // Send email
        String subject = "Welcome " + user.getFullName();
        String body = "Login account success";
        emailService.sendEmail(user.getEmail(), subject, body);
        return new ApiSuccessResponseDTO<>(authResponseDTO);
    }

    /**
     * Signup.
     * @param requestDTO UserRequestDTO.
     * @return AuthResponseDTO.
     */
    @Override
    @Transactional
    public ApiSuccessResponseDTO<AuthResponseDTO> signup(UserRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElse(null);
        if (user != null) {
            throw new BadRequestException(UserMessageError.EMAIL_USED);
        }

        User saveUser = userRepository.save(userMapper.toEntity(requestDTO));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                saveUser.getEmail(), saveUser.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponseDTO authResponseDTO = createAuthResponseDTO(token, saveUser);

        // Send email
        String subject = "Welcome " + saveUser.getFullName();
        String body = "Register account success";
        emailService.sendEmail(saveUser.getEmail(), subject, body);
        return new ApiSuccessResponseDTO<>(authResponseDTO);
    }

    /**
     * Create password reset token.
     * @param email String.
     * @return Boolean.
     */
    @Override
    @Transactional
    public ApiSuccessResponseDTO<Boolean> createPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusSeconds(JwtConstant.EXPIRATION_TIME))
                .build();
        passwordResetTokenRepository.save(passwordResetToken);

        // Send email
        String resetLink = Constant.FRONTEND_URL + token;
        String subject = "Password Reset Request";
        String body = "You requested to reset your password. Use this link (valid 5 minutes): " + resetLink;
        Boolean result = emailService.sendEmail(user.getEmail(), subject, body);
        return new ApiSuccessResponseDTO<>(result);
    }

    /**
     * Reset password.
     * @param token String.
     * @param newPassword String.
     * @return Boolean.
     */
    @Override
    @Transactional
    public ApiSuccessResponseDTO<Boolean> resetPassword(String token, String newPassword) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token).orElse(null);
        if (passwordResetToken == null) {
            throw new BadRequestException(PasswordResetTokenError.TOKEN_INVALID);
        }

        if (passwordResetToken.isExpired()) {
            throw new BadRequestException(PasswordResetTokenError.TOKEN_EXPIRED);
        }

        User user = passwordResetToken.getUser();

        // Check newPassword matches old password
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new BadRequestException(PasswordResetTokenError.NEW_PASSWORD_SAME_AS_OLD_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);

        // Send email
        String subject = "Welcome " + user.getFullName();
        String body = "Reset password success";
        Boolean result = emailService.sendEmail(user.getEmail(), subject, body);
        return new ApiSuccessResponseDTO<>(result);
    }

    /**
     * Authenticate.
     * @param email String.
     * @param password String.
     * @return Authentication.
     */
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new NotFoundException(UserMessageError.ACCOUNT_INVALID_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }

    /**
     * Create auth response DTO.
     * @param token String.
     * @param user User.
     * @return AuthResponseDTO.
     */
    private AuthResponseDTO createAuthResponseDTO(String token, User user) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        authResponseDTO.setUser(userMapper.toDTO(user));

        return authResponseDTO;
    }
}

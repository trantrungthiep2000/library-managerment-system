package com.example.librarymanagermentservice.mapper;

import com.example.librarymanagermentservice.common.enums.UserRoleEnum;
import com.example.librarymanagermentservice.dto.UserDTO;
import com.example.librarymanagermentservice.dto.request.UserRequestDTO;
import com.example.librarymanagermentservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Information about user mapper.
 */
@Service
@AllArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    /**
     * Mapper to DTO.
     * @param user User.
     * @return UserDTO.
     */
    public UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .lastLogin(user.getLastLogin())
                .build();
    }

    /**
     * Mapper to DTO list.
     * @param users List<User>.
     * @return List<UserDTO>.
     */
    public List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Mapper to entity.
     * @param requestDTO UserRequestDTO.
     * @return User.
     */
    public User toEntity(UserRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        return User.builder()
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .phoneNumber(requestDTO.getPhoneNumber())
                .fullName(requestDTO.getFullName())
                .role(UserRoleEnum.USER)
                .lastLogin(LocalDateTime.now())
                .build();
    }

    /**
     * Mapper to DTO set.
     * @param users Set<User>.
     * @return Set<UserDTO>.
     */
    public Set<UserDTO> toDTOSet(Set<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }
}

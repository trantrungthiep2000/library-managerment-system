package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.message.UserMessageError;
import com.example.librarymanagermentservice.dto.UserDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.UserMapper;
import com.example.librarymanagermentservice.model.User;
import com.example.librarymanagermentservice.repository.UserRepository;
import com.example.librarymanagermentservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Information about user repository.
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityContextService securityContextService;

    /**
     * Get current user.
     * @return UserDTO.
     */
    @Override
    public ApiSuccessResponseDTO<UserDTO> getCurrentUser() {
        User user = securityContextService.getCurrentUser().orElse(null);
        if (user == null) {
            throw new NotFoundException(UserMessageError.USER_NOT_FOUND);
        }

        UserDTO userDTO = userMapper.toDTO(user);
        return new ApiSuccessResponseDTO<>(userDTO);
    }

    /**
     * Get all users.
     * @return List<UserDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<List<UserDTO>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = userMapper.toDTOList(users);
        return new ApiSuccessResponseDTO<>(userDTOS);
    }
}

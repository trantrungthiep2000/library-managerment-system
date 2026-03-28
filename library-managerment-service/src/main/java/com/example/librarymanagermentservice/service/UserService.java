package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.dto.UserDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;

import java.util.List;

/**
 * Information about interface user service.
 */
public interface UserService {

    /**
     * Get current user.
     * @return UserDTO.
     */
    ApiSuccessResponseDTO<UserDTO> getCurrentUser();

    /**
     * Get all users.
     * @return List<UserDTO>.
     */
    ApiSuccessResponseDTO<List<UserDTO>> getUsers();
}

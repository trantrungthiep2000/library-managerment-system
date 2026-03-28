package com.example.librarymanagermentservice.controller;

import com.example.librarymanagermentservice.common.ApiRoutesConstant;
import com.example.librarymanagermentservice.dto.UserDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutesConstant.User.BASE_USER)
public class UserController {
    private final UserService userService;

    /**
     * Get profile user.
     * @return UserDTO.
     */
    @GetMapping(ApiRoutesConstant.User.PROFILE)
    public ResponseEntity<ApiSuccessResponseDTO<UserDTO>> getProfile() {
        var result = userService.getCurrentUser();
        return ResponseEntity.ok(result);
    }

    /**
     * Get all users.
     * @return List<UserDTO>.
     */
    @GetMapping(ApiRoutesConstant.User.USERS)
    public ResponseEntity<ApiSuccessResponseDTO<List<UserDTO>>> getUsers() {
        var result = userService.getUsers();
        return ResponseEntity.ok(result);
    }
}

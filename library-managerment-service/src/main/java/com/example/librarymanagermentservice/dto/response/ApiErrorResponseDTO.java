package com.example.librarymanagermentservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Information about api error response DTO.
 */
@Getter
@Setter
public class ApiErrorResponseDTO {
    private int status;
    private String code;
    private List<String> messages;
    private LocalDateTime timestamp;

    public ApiErrorResponseDTO(int status, String code, List<String> messages) {
        this.status = status;
        this.code = code;
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
    }
}

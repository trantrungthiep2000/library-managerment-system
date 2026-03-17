package com.example.librarymanagermentservice.dto;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * Information about api success response DTO.
 * @param <T> Entity.
 */
@Getter
@Setter
public class ApiSuccessResponseDTO<T> {
    private int status;
    private String message;
    private T data;

    public ApiSuccessResponseDTO(T data) {
        this.status = StatusCodeConstant.OK;
        this.message = StatusCodeStringConstant.OK;
        this.data = data;
    }
}

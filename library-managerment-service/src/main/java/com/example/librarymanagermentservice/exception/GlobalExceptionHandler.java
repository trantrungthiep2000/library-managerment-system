package com.example.librarymanagermentservice.exception;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;
import com.example.librarymanagermentservice.dto.response.ApiErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Information about global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle base exception.
     * @param ex BaseApiException.
     * @return ApiErrorResponse.
     */
    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleBaseException(BaseApiException ex) {
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
            ex.getStatus(),
            ex.getCode(),
            List.of(ex.getMessage())
        );

        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    /**
     * Handle exception.
     * @param ex Exception.
     * @return ApiErrorResponse.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDTO> handleException(Exception ex) {
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
            StatusCodeConstant.INTERNAL_SERVER_ERROR,
            StatusCodeStringConstant.INTERNAL_SERVER_ERROR,
            List.of(ex.getMessage())
        );

        log.error(ex.getMessage());
        return ResponseEntity.status(StatusCodeConstant.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Handle validation exception.
     * @param ex MethodArgumentNotValidException.
     * @return ApiErrorResponseDTO.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
            StatusCodeConstant.BAD_REQUEST,
            StatusCodeStringConstant.BAD_REQUEST,
            errors
        );

        log.error(errors.toString());
        return ResponseEntity.status(StatusCodeConstant.BAD_REQUEST).body(response);
    }
}

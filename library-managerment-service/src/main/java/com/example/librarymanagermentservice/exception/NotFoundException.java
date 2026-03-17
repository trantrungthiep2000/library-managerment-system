package com.example.librarymanagermentservice.exception;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;

/**
 * Information about not found exception.
 */
public class NotFoundException extends BaseApiException {
    public NotFoundException(String message) {
        super(StatusCodeConstant.NOT_FOUND, StatusCodeStringConstant.NOT_FOUND, message);
    }
}

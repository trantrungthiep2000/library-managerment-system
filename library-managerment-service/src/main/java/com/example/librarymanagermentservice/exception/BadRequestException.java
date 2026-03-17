package com.example.librarymanagermentservice.exception;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;

/**
 *  * Information about bad request exception.
 *  */
public class BadRequestException extends BaseApiException {
    public BadRequestException(String message) {
        super(StatusCodeConstant.BAD_REQUEST, StatusCodeStringConstant.BAD_REQUEST, message);
    }
}

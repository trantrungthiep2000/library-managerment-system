package com.example.librarymanagermentservice.common.enums;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;

/**
 * Information of status code enum.
 */
public enum StatusCodeEnum {
    OK(StatusCodeConstant.OK, StatusCodeStringConstant.OK),
    BAD_REQUEST(StatusCodeConstant.BAD_REQUEST, StatusCodeStringConstant.BAD_REQUEST),
    UNAUTHORIZED(StatusCodeConstant.UNAUTHORIZED, StatusCodeStringConstant.UNAUTHORIZED),
    FORBIDDEN(StatusCodeConstant.FORBIDDEN, StatusCodeStringConstant.FORBIDDEN),
    NOT_FOUND(StatusCodeConstant.NOT_FOUND, StatusCodeStringConstant.NOT_FOUND),
    INTERNAL_SERVER_ERROR(StatusCodeConstant.INTERNAL_SERVER_ERROR, StatusCodeStringConstant.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;

    StatusCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

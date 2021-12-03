package com.example.ewears.exceptions;

import lombok.Getter;

public class SuccessCodeWithErrorResponseException extends RuntimeException {

    @Getter
    private ErrorResponse errorResponse;

    @Getter
    private String userId;

    public SuccessCodeWithErrorResponseException(String userId, ErrorResponse errorResponse) {

        this.userId = userId;

        this.errorResponse = errorResponse;
    }

}

package com.example.cafe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomError {
    ;

    private final int status;
    private final String code;
    private final String message;
}

package com.example.cafe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomError {
    NOT_ENOUGH_POINT(HttpStatus.BAD_REQUEST.value(), "M001", "포인트가 부족합니다."),;

    private final int status;
    private final String code;
    private final String message;
}

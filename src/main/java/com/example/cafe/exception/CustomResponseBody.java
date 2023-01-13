package com.example.cafe.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponseBody {
    private int httpStatus;
    private String code;
    private String message;
}

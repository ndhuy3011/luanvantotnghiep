package com.stu.luanvan.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private HttpStatus httpStatus;
    private String message;
}

package com.sourabh.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;

}

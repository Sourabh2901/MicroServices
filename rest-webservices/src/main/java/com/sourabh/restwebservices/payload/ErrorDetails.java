package com.sourabh.restwebservices.payload;

import lombok.*;

@Data @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ErrorDetails {
    private String message;
    private String status;
}

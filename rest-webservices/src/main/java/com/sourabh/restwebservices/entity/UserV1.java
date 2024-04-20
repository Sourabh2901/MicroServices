package com.sourabh.restwebservices.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserV1 {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}

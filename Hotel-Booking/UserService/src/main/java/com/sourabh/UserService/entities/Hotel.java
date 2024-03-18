package com.sourabh.UserService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Hotel {

    private String hotelId;
    private String name;
    private String location;
    private String about;
}

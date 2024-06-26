package com.sourabh.HotelService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Hotel {

    @Id
    private String hotelId;
    private String name;
    private String location;
    private String about;

}

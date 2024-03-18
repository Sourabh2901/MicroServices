package com.sourabh.HotelService.services;

import com.sourabh.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

//    create
    Hotel create(Hotel hotel);

//    get All Hotel
    List<Hotel> getAll();

//    get Single Hotel
    Hotel get(String hotelId);
}

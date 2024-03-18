package com.sourabh.RatingService.services;

import com.sourabh.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

//    create
    Rating create(Rating rating);

//    get All rating
    List<Rating> getAllRating();

//    get Rating By UserId
    List<Rating> getRatingByUserId(String userId);

//    get Rating By Hotel Id
    List<Rating> getRatingByHotelId(String hotelId);

}

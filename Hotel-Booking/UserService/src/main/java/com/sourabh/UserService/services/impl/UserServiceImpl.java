package com.sourabh.UserService.services.impl;

import com.sourabh.UserService.Feign.HotelService;
import com.sourabh.UserService.Feign.RatingService;
import com.sourabh.UserService.entities.Hotel;
import com.sourabh.UserService.entities.Rating;
import com.sourabh.UserService.entities.User;
import com.sourabh.UserService.execeptions.ResourceNotFoundException;
import com.sourabh.UserService.repositories.UserRepository;
import com.sourabh.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);



        return repo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = repo.findAll();
        for(User user : userList){
//            Rating [] ratingList = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

            List<Rating> list = ratingService.getRatingByUserId(user.getUserId());

            List<Rating> ratingListWithHotel = list.stream()
                    .map(rating -> {
//                        Hotel hotelObj = restTemplate.getForObject("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                        Hotel hotelobj = hotelService.getHotel(rating.getHotelId());
                        rating.setHotel(hotelobj);
                        return rating;
                    })
                    .collect(Collectors.toList());

            user.setRatings(ratingListWithHotel);
        }
        return userList;
    }

    @Override
    public User getUser(String userId) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));

//        finding ratings of user by userId << Using RestTemplate for calling other microservices >>
//        Rating [] ratingList = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);

//        using Feign client to call other microservices
        List<Rating> ratings = ratingService.getRatingByUserId(userId);

//        finding hotel w.r.t particular ratingId
        List<Rating> ratingListWithHotel = ratings.stream()
                .map(rating -> {
//                    Hotel hotelObj = restTemplate.getForObject("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                    Hotel hotelObj = hotelService.getHotel(rating.getHotelId());
                    rating.setHotel(hotelObj);
                    return rating;
                })
                .collect(Collectors.toList());

        user.setRatings(ratingListWithHotel);
        return user;
    }
}

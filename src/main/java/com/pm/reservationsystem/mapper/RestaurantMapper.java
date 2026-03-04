package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.RestaurantRequestDto;
import com.pm.reservationsystem.dto.RestaurantResponseDto;
import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.model.Restaurant;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RestaurantMapper {

    public RestaurantResponseDto toRestaurantDto(Restaurant restaurant) {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();
        restaurantResponseDto.setId(restaurant.getId());
        restaurantResponseDto.setName(restaurant.getName());
        restaurantResponseDto.setAddress(restaurant.getAddress());
        restaurantResponseDto.setCreatedAt(restaurant.getCreatedAt());
        return restaurantResponseDto;
    }

    public Restaurant toRestaurant(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequestDto.getName())
                .address(restaurantRequestDto.getAddress())
                .timeZone(restaurantRequestDto.getTimezone())
                .build();

        Policy policy = Policy.builder()
                .restaurant(restaurant)
                .build();
        restaurant.setPolicy(policy);
        return restaurant;

    }
}

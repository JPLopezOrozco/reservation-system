package com.pm.reservationsystem.mapper;

import com.pm.reservationsystem.dto.RestaurantResponseDto;
import com.pm.reservationsystem.model.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RestaurantMapper {

    private final TableMapper tableMapper;
    private final ReservationMapper reservationMapper;

    public RestaurantResponseDto toRestaurantDto(Restaurant restaurant) {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();
        restaurantResponseDto.setId(restaurant.getId());
        restaurantResponseDto.setName(restaurant.getName());
        restaurantResponseDto.setAddress(restaurant.getAddress());
        restaurantResponseDto.setTables(
                restaurant.getTables().stream()
                        .map(tableMapper::toTableResponseDto)
                        .collect(Collectors.toSet())
        );
        return restaurantResponseDto;
    }
}

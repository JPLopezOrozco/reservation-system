package com.pm.reservationsystem.service;

import com.pm.reservationsystem.dto.RestaurantRequestDto;
import com.pm.reservationsystem.dto.RestaurantResponseDto;
import com.pm.reservationsystem.exception.RestaurantNotFoundException;
import com.pm.reservationsystem.mapper.RestaurantMapper;
import com.pm.reservationsystem.model.Policy;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;


    @Transactional
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        ZoneId.of(restaurantRequestDto.getTimezone());

        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantRequestDto);

        Policy policy = Policy.builder()
                .restaurant(restaurant)
                .build();
        restaurant.setPolicy(policy);

        Restaurant saved = restaurantRepository.save(restaurant);
        log.info("Created restaurant with id {}", restaurant.getId());
        return restaurantMapper.toRestaurantDto(saved);
    }

    @Transactional(readOnly = true)
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toRestaurantDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public RestaurantResponseDto getRestaurantById(Long id) {
        return restaurantMapper.toRestaurantDto(restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant with id " + id + " not found")));
    }
    @Transactional(readOnly = true)
    public RestaurantResponseDto getRestaurantByName(String name) {
        return restaurantMapper.toRestaurantDto(restaurantRepository.findByName(name)
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant with name " + name + " not found")));
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException("Restaurant with id " + id + " not found");
        }
        restaurantRepository.deleteById(id);
        log.info("Restaurant with id {} deleted successfully", id);
    }

}

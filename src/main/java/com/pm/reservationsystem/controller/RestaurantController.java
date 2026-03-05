package com.pm.reservationsystem.controller;

import com.pm.reservationsystem.dto.RestaurantRequestDto;
import com.pm.reservationsystem.dto.RestaurantResponseDto;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        RestaurantResponseDto restaurant = restaurantService.createRestaurant(restaurantRequestDto);
        var location = UriComponentsBuilder
                .fromPath("/restaurants/{restaurantId}")
                .buildAndExpand(restaurant.getId())
                .toUri();
        return ResponseEntity.created(location).body(restaurant);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long id) {
        RestaurantResponseDto restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        List<RestaurantResponseDto> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/name")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByName(@RequestParam String name){
        RestaurantResponseDto restaurant = restaurantService.getRestaurantByName(name);
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }




}

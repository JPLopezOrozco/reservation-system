package com.pm.reservationsystem.service;

import com.pm.reservationsystem.dto.TableRequestDto;
import com.pm.reservationsystem.dto.TableResponseDto;
import com.pm.reservationsystem.exception.RestaurantNotFoundException;
import com.pm.reservationsystem.exception.TableException;
import com.pm.reservationsystem.exception.TableNotFoundException;
import com.pm.reservationsystem.mapper.TableMapper;
import com.pm.reservationsystem.model.Restaurant;
import com.pm.reservationsystem.model.Table;
import com.pm.reservationsystem.model.TableGroup;
import com.pm.reservationsystem.repository.RestaurantRepository;
import com.pm.reservationsystem.repository.TableGroupRepository;
import com.pm.reservationsystem.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableService {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableGroupRepository tableGroupRepository;
    private final TableMapper tableMapper;


    @Transactional
    public TableResponseDto createTable(TableRequestDto tableRequestDto) {
        if (tableRequestDto.getMaxCapacity()<tableRequestDto.getMinCapacity()){
            throw new TableException("minCapacity cannot be greater than maxCapacity");
        }
        Restaurant restaurant = restaurantRepository.findById(tableRequestDto.getRestaurantId())
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found"));

        TableGroup tableGroup = null;
        if (tableRequestDto.getTableGroupId() != null) {
            tableGroup = tableGroupRepository.findById(tableRequestDto.getTableGroupId())
                    .orElseThrow(() -> new TableException("TableGroup not found: " + tableRequestDto.getTableGroupId()));
        }

        Table table = tableMapper.toTable(tableRequestDto, restaurant, tableGroup);
        tableRepository.save(table);
        return tableMapper.toTableResponseDto(table);
    }

    @Transactional(readOnly = true)
    public List<TableResponseDto> getAllTablesByRestaurant(Long restaurantId) {
        return tableRepository.findAllByRestaurantId(restaurantId).stream()
                .map(tableMapper::toTableResponseDto)
                .toList();
    }

    @Transactional
    public void deleteTable(Long tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(()-> new TableNotFoundException("Table not found"));
        tableRepository.delete(table);
    }

    @Transactional(readOnly = true)
    public List<TableResponseDto> getTablesByRestaurantAndGroup(Long restaurantId, Long groupId) {
        if (!restaurantRepository.existsById(restaurantId) || !tableGroupRepository.existsById(groupId)) {
            throw new TableException("Restaurant or Table group not found");
        }

        return tableRepository.findAllByRestaurantIdAndTableGroupId(restaurantId, groupId).stream()
                .map(tableMapper::toTableResponseDto)
                .toList();
    }



}

package com.pm.reservationsystem.service;

import com.pm.reservationsystem.dto.TableGroupRequestDto;
import com.pm.reservationsystem.dto.TableGroupResponseDto;
import com.pm.reservationsystem.model.TableGroup;
import com.pm.reservationsystem.repository.TableGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableGroupService {


    private final TableGroupRepository tableGroupRepository;


    public TableGroupResponseDto createTableGroup(TableGroupRequestDto tableGroupRequestDto) {
        TableGroup tableGroup = TableGroup.builder()
                .restaurantId(tableGroupRequestDto.getRestaurantId())
                .name(tableGroupRequestDto.getName())
                .build();
        TableGroup saved = tableGroupRepository.save(tableGroup);

        return TableGroupResponseDto.builder()
                .id(saved.getId())
                .restaurantId(saved.getRestaurantId())
                .name(saved.getName())
                .build();
    }
}

package com.pm.reservationsystem.dto;

import com.pm.reservationsystem.model.TableGroup;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableResponseDto {
    private String tableName;
    private int minCapacity;
    private int maxCapacity;
    private String tableGroup;
}

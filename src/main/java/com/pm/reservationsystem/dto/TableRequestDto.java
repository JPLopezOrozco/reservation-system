package com.pm.reservationsystem.dto;

import com.pm.reservationsystem.model.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableRequestDto {
    @NotNull
    private Long restaurantId;
    @NotBlank
    private String tableName;
    @NotNull @Min(1)
    private Integer minCapacity;
    @NotNull @Min(1)
    private Integer maxCapacity;
    private Long tableGroupId;
}

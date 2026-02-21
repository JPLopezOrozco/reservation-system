package com.pm.reservationsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@jakarta.persistence.Table(name = "restaurant_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Restaurant restaurant;
    @Column(nullable = false, length = 20)
    private String tableName;
    @Column(nullable = false)
    private int minCapacity;
    @Column(nullable = false)
    private int maxCapacity;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private TableGroup tableGroup;
}

package com.pm.reservationsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 50)
    private String timeZone;
    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Policy policy;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Table> tables = new HashSet<>();
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Reservation> reservations = new HashSet<>();
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    @Version
    private Integer version;
}

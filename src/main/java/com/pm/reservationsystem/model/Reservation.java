package com.pm.reservationsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private Table table;
    @Column(nullable = false)
    private Instant startTime;
    @Column(nullable = false)
    private Instant expiredAt;
    @Column(nullable = false)
    private int partySize;
    @Column(nullable = false)
    private int durationMin;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(length = 250)
    private String specialNotes;
    @CreationTimestamp
    private Instant created;
    @UpdateTimestamp
    private Instant modified;
    @Version
    private Integer version;

}

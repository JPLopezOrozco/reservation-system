package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Table;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
    List<Table> findAllByRestaurantId(Long restaurantId);
    List<Table> findAllByRestaurantIdAndTableGroupId(Long restaurantId, Long groupId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from Table t where t.id = :id")
    Table findTableByIdForUpdate(@Param("id") Long id);
}

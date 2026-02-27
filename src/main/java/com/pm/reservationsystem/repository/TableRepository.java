package com.pm.reservationsystem.repository;

import com.pm.reservationsystem.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}

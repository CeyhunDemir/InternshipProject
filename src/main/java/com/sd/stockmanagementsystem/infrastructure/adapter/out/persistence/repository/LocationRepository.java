package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.LocationRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LocationRepository extends JpaRepository<Location, Long>, LocationRepositoryPort {
    Optional<Location> findById(long id);

    Optional<Location> findByName(String name);

    List<Location> findByNameIn(Set<String> names);

    List<Location> findByIdIn(Set<Long> ids);
}

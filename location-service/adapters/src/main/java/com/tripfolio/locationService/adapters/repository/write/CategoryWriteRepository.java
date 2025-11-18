package com.tripfolio.locationService.adapters.repository.write;

import com.tripfolio.locationService.adapters.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryWriteRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}

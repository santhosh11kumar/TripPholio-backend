package com.tripfolio.locationService.adapters.repository.write;

import com.tripfolio.locationService.adapters.repository.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagWriteRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}

package com.tripfolio.locationService.adapters.repository.write;

import com.tripfolio.locationService.adapters.repository.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceWriteRepository extends JpaRepository<Place, Long> {
}

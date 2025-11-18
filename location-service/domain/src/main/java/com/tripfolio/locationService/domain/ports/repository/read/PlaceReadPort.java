package com.tripfolio.locationService.domain.ports.repository.read;

import com.tripfolio.locationService.domain.model.PlaceNearbyRecord;
import com.tripfolio.locationService.domain.model.PlaceRecord;

import java.util.List;

public interface PlaceReadPort {
    List<PlaceNearbyRecord> findByCoordinates(double lat, double lng, double radiusMeters, String category);

    PlaceRecord searchPlacesByText(String query);
}

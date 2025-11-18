package com.tripfolio.locationService.domain.useCase;

import com.tripfolio.locationService.domain.model.PlaceNearbyRecord;
import com.tripfolio.locationService.domain.model.PlaceRecord;
import com.tripfolio.locationService.domain.ports.repository.read.PlaceReadPort;
import com.tripfolio.locationService.domain.ports.repository.write.PlaceWritePort;
import com.tripfolio.locationService.domain.util.InputSanitizer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    public final PlaceReadPort placeReadPort;
    public final PlaceWritePort placeWritePort;
    public final InputSanitizer inputSanitizer;

    public PlaceService(PlaceReadPort placeReadPort, PlaceWritePort placeWritePort, InputSanitizer inputSanitizer) {
        this.placeReadPort = placeReadPort;
        this.placeWritePort = placeWritePort;
        this.inputSanitizer = inputSanitizer;
    }

    @Transactional
    public boolean createPlace(PlaceRecord record) {
        return placeWritePort.createPlace(record);
    }

    public List<PlaceNearbyRecord> findNearby(double lat, double lng, double radiusMeters, String category) {
        return placeReadPort.findByCoordinates(lat, lng, radiusMeters, category);
    }

    public PlaceRecord  searchPlacesByText(String query) {
        String clean = inputSanitizer.sanitizeForSearch(query);
        if (clean == null) throw new RuntimeException("Query is not secure"); // or throw BadRequestException based on your policy
        return placeReadPort.searchPlacesByText(query);
    }
}

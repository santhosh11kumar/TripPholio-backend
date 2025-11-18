package com.tripfolio.locationService.adapters.repository.read;

import com.tripfolio.locationService.adapters.api.outbound.mapper.PlaceRequestMapper;
import com.tripfolio.locationService.adapters.enums.PlaceStatus;
import com.tripfolio.locationService.adapters.repository.Mapper.PlaceReadAdapaterMapper;
import com.tripfolio.locationService.adapters.repository.entity.Place;
import com.tripfolio.locationService.adapters.repository.projection.PlaceNearbyProjection;
import com.tripfolio.locationService.domain.model.PlaceNearbyRecord;
import com.tripfolio.locationService.domain.model.PlaceRecord;
import com.tripfolio.locationService.domain.ports.repository.read.PlaceReadPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceReadAdapter implements PlaceReadPort {
    public final PlaceReadRepository readRepository;
    public PlaceReadAdapter(PlaceReadRepository readRepository) {
        this.readRepository = readRepository;
    }
    @Override
    public List<PlaceNearbyRecord> findByCoordinates(double lat, double lng, double radiusMeters, String category) {
        List<PlaceNearbyProjection> places = readRepository.findNearbyPlaces(lat, lng, radiusMeters, category, PlaceStatus.PENDING.name());
        return places.stream()
                .map(PlaceReadAdapaterMapper.instance::toPlaceNearByRecord)
                .collect(Collectors.toList());
    }

    @Override
    public PlaceRecord searchPlacesByText(String query) {
        return PlaceRequestMapper.instance.toRecord(readRepository.findByNameContainingIgnoreCase(query));
    }
}

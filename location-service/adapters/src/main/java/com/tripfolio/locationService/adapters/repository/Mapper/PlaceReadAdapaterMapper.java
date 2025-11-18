package com.tripfolio.locationService.adapters.repository.Mapper;

import com.tripfolio.locationService.adapters.repository.projection.PlaceNearbyProjection;
import com.tripfolio.locationService.domain.model.PlaceNearbyRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaceReadAdapaterMapper {
    PlaceReadAdapaterMapper instance = Mappers.getMapper(PlaceReadAdapaterMapper.class);

    PlaceNearbyRecord toPlaceNearByRecord(PlaceNearbyProjection place);
}

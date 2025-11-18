package com.tripfolio.locationService.domain.ports.repository.write;

import com.tripfolio.locationService.domain.model.PlaceRecord;

public interface PlaceWritePort {
    boolean createPlace(PlaceRecord record);
}

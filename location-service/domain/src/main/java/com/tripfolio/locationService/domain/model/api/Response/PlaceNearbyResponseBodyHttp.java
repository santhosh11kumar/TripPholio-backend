package com.tripfolio.locationService.domain.model.api.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlaceNearbyResponseBodyHttp {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private double distanceMeters;  // calculated via PostGIS
    private String category;
    private double averageRating;

}

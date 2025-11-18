package com.tripfolio.locationService.adapters.repository.projection;

public interface PlaceNearbyProjection {
    Long getId();
    String getName();
    double getLatitude();
    double getLongitude();
    double getDistanceMeters();
    String getCategory();
    double getAverageRating();
}

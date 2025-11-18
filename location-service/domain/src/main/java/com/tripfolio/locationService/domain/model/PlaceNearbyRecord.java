package com.tripfolio.locationService.domain.model;
import lombok.Builder;

@Builder
public record PlaceNearbyRecord(
        Long id,
        String name,
        double latitude,
        double longitude,
        double distanceMeters,
        String category,
        double averageRating
) {}

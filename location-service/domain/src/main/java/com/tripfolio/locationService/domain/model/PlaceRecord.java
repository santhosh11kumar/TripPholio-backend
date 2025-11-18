package com.tripfolio.locationService.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Builder
public record PlaceRecord(
        Long id,
        String name,
        String slug,
        String description,
        CategoryRecord primaryCategory,
        List<TagRecord> tags,
        String street,
        String city,
        String state,
        String country,
        String postalCode,
        double latitude,
        double longitude,
        String phone,
        String website,
        Map<String, String> openingHours,      // JSON string
        Integer priceLevel,
        Map<String, String> sourceIds,        // JSON string for external IDs
        RatingSnapshot ratingSnapshot,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

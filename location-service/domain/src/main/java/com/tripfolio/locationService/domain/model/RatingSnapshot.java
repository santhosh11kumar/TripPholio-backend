package com.tripfolio.locationService.domain.model;
import lombok.Builder;

@Builder
public record RatingSnapshot(
        double average,
        int count
) {}

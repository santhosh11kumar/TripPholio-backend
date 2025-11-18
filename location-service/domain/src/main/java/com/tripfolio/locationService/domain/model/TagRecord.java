package com.tripfolio.locationService.domain.model;

import lombok.Builder;

@Builder
public record TagRecord(
        Long id,
        String name
) {}
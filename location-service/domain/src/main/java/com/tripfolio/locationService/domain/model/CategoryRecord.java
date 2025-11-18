package com.tripfolio.locationService.domain.model;

import lombok.Builder;

@Builder
public record CategoryRecord(
        Long id,
        String name
) {}
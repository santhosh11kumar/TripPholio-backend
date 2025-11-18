package com.tripfolio.locationService.domain.model.api.Request;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceRequestBodyHttp {

    // Basic info
    private String name;
    private String slug;
    private String description;

    // Address info
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    // Geo coordinates
    private Double latitude;
    private Double longitude;

    // Category & tags
    private String categoryName;
    private List<String> tags;

    // Optional info
    private String phone;
    private String website;
    private Integer priceLevel;

    // JSON fields (can be stringified JSON from frontend)
    private Map<String, String> openingHours;
    private Map<String, String> sourceIds;

    // Whether active or not
    private Boolean isActive = true;
}


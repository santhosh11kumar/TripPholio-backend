package com.tripfolio.locationService.domain.model.api.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class PlaceSearchResponseBodyHttp {
    private String name;
    private String slug;
    private String description;
    private String category;
    private List<String> tags;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private double latitude;
    private double longitude;
    private String phone;
    private String website;
    private Map<String, String> openingHours;
    private Integer priceLevel;
    private Map<String, String> sourceIds;
}

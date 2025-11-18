package com.tripfolio.locationService.domain.model.api.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PlaceDetailResponseBodyHttp {private Long id;
    private String name;
    private String description;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private double latitude;
    private double longitude;
    private String phone;
    private String website;
    private String openingHours;
    private Integer priceLevel;
    private String primaryCategory;
    private List<String> tags;
    private double averageRating;
    private int ratingCount;

}

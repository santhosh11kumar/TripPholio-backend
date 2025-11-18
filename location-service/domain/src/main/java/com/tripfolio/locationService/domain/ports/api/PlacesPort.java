package com.tripfolio.locationService.domain.ports.api;

import com.tripfolio.locationService.domain.model.api.Request.PlaceRequestBodyHttp;
import com.tripfolio.locationService.domain.model.api.Response.PlaceNearbyResponseBodyHttp;
import com.tripfolio.locationService.domain.model.api.Response.PlaceSearchResponseBodyHttp;
import com.tripfolio.locationService.domain.util.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlacesPort {
    ResponseEntity<ResponseBody<Boolean>> createPlace(@RequestBody PlaceRequestBodyHttp requestBodyHttp);
    ResponseEntity<ResponseBody<List<PlaceNearbyResponseBodyHttp>>> findNearby(double lat, double lng, double radiusMeters, String category);

    ResponseEntity<ResponseBody<PlaceSearchResponseBodyHttp>> searchByText(String query);
//
//    ResponseEntity<ResponseBody<PlaceDetailResponseBodyHttp>> getPlaceWithDetails(Long id);
}

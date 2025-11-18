package com.tripfolio.locationService.adapters.api.inbound;

import com.tripfolio.locationService.adapters.api.inbound.mapper.PlaceResponseMapper;
import com.tripfolio.locationService.adapters.api.outbound.mapper.PlaceRequestMapper;
import com.tripfolio.locationService.domain.model.PlaceRecord;
import com.tripfolio.locationService.domain.model.api.Request.PlaceRequestBodyHttp;
import com.tripfolio.locationService.domain.model.api.Response.PlaceNearbyResponseBodyHttp;
import com.tripfolio.locationService.domain.model.api.Response.PlaceSearchResponseBodyHttp;
import com.tripfolio.locationService.domain.ports.api.PlacesPort;
import com.tripfolio.locationService.domain.useCase.PlaceService;
import com.tripfolio.locationService.domain.util.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/places")
@RestController
public class PlaceAdapter implements PlacesPort {

    public final PlaceService placeService;

    public PlaceAdapter(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<ResponseBody<Boolean>> createPlace(@RequestBody PlaceRequestBodyHttp requestBodyHttp) {
        boolean created = placeService.createPlace(PlaceRequestMapper.instance.toPlaceRecord(requestBodyHttp));
        ResponseBody<Boolean> body = ResponseBody.<Boolean>builder()
                .data(true)
                .build();

        return ResponseEntity.ok(body);
    }

    @GetMapping("/nearby")
    @Override
    public ResponseEntity<ResponseBody<List<PlaceNearbyResponseBodyHttp>>> findNearby(
            @RequestParam("lat") double lat,
            @RequestParam("lng") double lng,
            @RequestParam("radiusMeters") double radiusMeters,
            @RequestParam(value = "category", required = false) String category) {
        List<PlaceNearbyResponseBodyHttp> response = placeService.findNearby(lat, lng, radiusMeters, category).stream()
                .map(PlaceResponseMapper.instance::toPlaceNearbyResponseBodyHttp)
                .collect(Collectors.toList());

        ResponseBody<List<PlaceNearbyResponseBodyHttp>> body = ResponseBody.<List<PlaceNearbyResponseBodyHttp>>builder()
                .data(response)
                .build();

        return ResponseEntity.ok(body);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<ResponseBody<PlaceSearchResponseBodyHttp>> searchByText(@RequestParam("query") String query) {
        ResponseBody<PlaceSearchResponseBodyHttp> body = ResponseBody.<PlaceSearchResponseBodyHttp>builder()
                .data(PlaceResponseMapper.instance.toPlaceSearchResponseBodyHttp(placeService.searchPlacesByText(query)))
                .build();
        return ResponseEntity.ok(body);
    }

}

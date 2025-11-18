package com.tripfolio.locationService.adapters.api.inbound.mapper;

import com.tripfolio.locationService.domain.model.PlaceNearbyRecord;
import com.tripfolio.locationService.domain.model.PlaceRecord;
import com.tripfolio.locationService.domain.model.TagRecord;
import com.tripfolio.locationService.domain.model.api.Response.PlaceNearbyResponseBodyHttp;
import com.tripfolio.locationService.domain.model.api.Response.PlaceSearchResponseBodyHttp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PlaceResponseMapper {
    PlaceResponseMapper instance = Mappers.getMapper(PlaceResponseMapper.class);

    PlaceNearbyResponseBodyHttp toPlaceNearbyResponseBodyHttp(PlaceNearbyRecord record);
    @Mappings({
        @Mapping(target = "category", source = "primaryCategory.name"),
        @Mapping(target = "tags", source = "tags", qualifiedByName = "toTagsList")
    })
    PlaceSearchResponseBodyHttp toPlaceSearchResponseBodyHttp(PlaceRecord record);

    @Named("toTagsList")
    default List<String> toTagsList(List<TagRecord> tagRecords) {
        if (tagRecords == null || tagRecords.isEmpty()) {
            return List.of();
        }
        return tagRecords.stream()
                .map(TagRecord::name)
                .collect(Collectors.toList());
    }
}

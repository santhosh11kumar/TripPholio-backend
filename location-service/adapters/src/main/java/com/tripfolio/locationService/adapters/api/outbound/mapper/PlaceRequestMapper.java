package com.tripfolio.locationService.adapters.api.outbound.mapper;

import com.tripfolio.locationService.adapters.repository.entity.Place;
import com.tripfolio.locationService.domain.model.CategoryRecord;
import com.tripfolio.locationService.domain.model.PlaceRecord;
import com.tripfolio.locationService.domain.model.TagRecord;
import com.tripfolio.locationService.domain.model.api.Request.PlaceRequestBodyHttp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PlaceRequestMapper {
    PlaceRequestMapper instance = Mappers.getMapper(PlaceRequestMapper.class);
    @Mappings({
            @Mapping(target = "primaryCategory", source = "categoryName", qualifiedByName = "toCategoryRecord"),
            @Mapping(target = "tags", source = "tags", qualifiedByName = "toTagRecords"),
            @Mapping(target = "ratingSnapshot", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    })
    PlaceRecord toPlaceRecord(PlaceRequestBodyHttp place);

    @Named("toCategoryRecord")
    default CategoryRecord toCategoryRecord(String categoryName) {
        if (categoryName == null) return null;
        return CategoryRecord.builder().name(categoryName).build();
    }

    @Named("toTagRecords")
    default List<TagRecord> toTagRecords(List<String> tagNames) {
        if (tagNames == null) return List.of();
        return tagNames.stream()
                .map(name -> TagRecord.builder().name(name).build())
                .collect(Collectors.toList());
    }

    Place toPlaceEntity(PlaceRecord place);

    PlaceRecord toRecord(Place place);
}

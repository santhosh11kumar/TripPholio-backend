package com.tripfolio.locationService.adapters.repository.write;

import com.tripfolio.locationService.adapters.api.outbound.mapper.PlaceRequestMapper;
import com.tripfolio.locationService.adapters.repository.entity.Category;
import com.tripfolio.locationService.adapters.repository.entity.Place;
import com.tripfolio.locationService.adapters.repository.entity.Tag;
import com.tripfolio.locationService.domain.model.PlaceRecord;
import com.tripfolio.locationService.domain.ports.repository.write.PlaceWritePort;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceWriteAdapter implements PlaceWritePort {
    public final PlaceWriteRepository placeWriteRepository;
    public final CategoryWriteRepository categoryRepository;
    public final TagWriteRepository tagWriteRepository;
    public PlaceWriteAdapter(PlaceWriteRepository placeWriteRepository, CategoryWriteRepository categoryRepository, TagWriteRepository tagWriteRepository) {
        this.tagWriteRepository = tagWriteRepository;
        this.categoryRepository = categoryRepository;
        this.placeWriteRepository = placeWriteRepository;
    }
    @Override
    public boolean createPlace(PlaceRecord record) {
        Place place = PlaceRequestMapper.instance.toPlaceEntity(record);
        Category category = categoryRepository.findByName(place.getName())
                .orElseGet(() -> categoryRepository.save(Category.builder().name(place.getPrimaryCategory().getName()).build()));
        List<Tag> tags = place.getTags().stream()
                .map(tag -> tagWriteRepository.findByName(tag.getName())
                        .orElseGet(() -> tagWriteRepository.save(
                                Tag.builder().name(tag.getName()).build()
                        ))
                )
                .collect(Collectors.toList());
        System.out.println(tags);
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Point location = geometryFactory.createPoint(new Coordinate(place.getLongitude(), place.getLatitude()));
        place.setLocation(location);
        place.setTags(tags);
        place.setPrimaryCategory(category);
        Place response =placeWriteRepository.save(place);
        return response.getId() != null;
    }
}

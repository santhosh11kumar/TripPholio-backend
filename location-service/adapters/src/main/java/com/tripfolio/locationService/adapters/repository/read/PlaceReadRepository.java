package com.tripfolio.locationService.adapters.repository.read;

import com.tripfolio.locationService.adapters.repository.entity.Place;
import com.tripfolio.locationService.adapters.repository.projection.PlaceNearbyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceReadRepository extends JpaRepository<Place, Long> {
    @Query(value = """
        SELECT 
            p.id AS id,
            p.name AS name,
            ST_Y(p.location::geometry) AS latitude,
            ST_X(p.location::geometry) AS longitude,
            ST_DistanceSphere(p.location, ST_MakePoint(:lng, :lat)) AS distanceMeters,
            c.name AS category,
            p.average_rating AS averageRating
        FROM location_service.place p
        JOIN location_service.category c 
            ON c.id = p.primary_category_id
        WHERE p.status = :status
          AND (:category IS NULL OR c.name = :category)
          AND ST_DWithin(
                p.location::geography,
                ST_MakePoint(:lng, :lat)::geography,
                :radiusMeters
          )
        ORDER BY ST_DistanceSphere(p.location, ST_MakePoint(:lng, :lat))
        """, nativeQuery = true)
    List<PlaceNearbyProjection> findNearbyPlaces(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("radiusMeters") double radiusMeters,
            @Param("category") String category,
            @Param("status") String status
    );

    @Query
    Place findByNameContainingIgnoreCase(String query);
}

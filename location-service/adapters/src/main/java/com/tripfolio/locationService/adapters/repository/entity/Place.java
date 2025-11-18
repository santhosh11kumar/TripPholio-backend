package com.tripfolio.locationService.adapters.repository.entity;

import com.tripfolio.locationService.adapters.enums.PlaceStatus;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "place", schema = "location_service")
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_seq")
    @SequenceGenerator(name = "place_seq", sequenceName = "place_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Address components
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    // Geo fields (PostGIS compatible)
    private Double latitude;
    private Double longitude;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;  // use org.locationtech.jts.geom.Point

    // Relations
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "primary_category_id")
    private Category primaryCategory;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "place_tags",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    // Optional fields
    private String phone;
    private String website;
    private Integer priceLevel;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> openingHours;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> sourceIds;

    private Boolean isActive = true;
    private Double averageRating = 0.0;
    private Integer ratingCount = 0;

    @Enumerated(EnumType.STRING)
    private PlaceStatus status = PlaceStatus.PENDING;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

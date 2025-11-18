package com.tripfolio.locationService.adapters.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category", schema = "location_service")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;
}
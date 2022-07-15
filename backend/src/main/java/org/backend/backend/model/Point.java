package org.backend.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "points")
@Getter
@Setter

public class Point {
    @Id
    @SequenceGenerator(name = "point_id", sequenceName = "point_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_id")
    @Column(name = "point_id")
    private Long id;
    private Float x;
    private Float y;
    private Float radius;
    private Boolean result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Point(Float x, Float y, Float radius, Boolean result) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.result = result;
    }

    public Point(Float x, Float y, Float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Point() {

    }
}

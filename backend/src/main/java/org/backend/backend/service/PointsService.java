package org.backend.backend.service;

import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;

import java.util.List;

public interface PointsService {
    Point getById(Long id);
    void save(Point point, Owner owner);
    List<Point> getAll(Owner owner);
}

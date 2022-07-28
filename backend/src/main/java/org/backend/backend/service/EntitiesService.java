package org.backend.backend.service;

import org.backend.backend.exception.PointValidationException;
import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;

import java.util.List;

public interface EntitiesService {
    boolean addNewOwner(Owner owner);

    boolean existsOwnerByName(String name);
    boolean existsOwnerByNameAndPassword(String name, String Password);

    void savePoint(Point point, String username) throws PointValidationException;
    List<Point> getAllPoints(String username);
}

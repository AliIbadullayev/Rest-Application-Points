package org.backend.backend.repository;

import org.backend.backend.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query(value = "SELECT new Point (e.x, e.y, e.radius, e.result) FROM Point e where e.owner.username = :username ")
    List<Point> findAllPointsByOwnerName(@Param(value = "username") String username);
}

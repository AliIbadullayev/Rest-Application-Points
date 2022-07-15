package org.backend.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.backend.backend.repository.PointRepository;

import java.util.List;

@Slf4j
@Service
public class PointsServiceImpl implements PointsService{
    @Autowired
    PointRepository pointRepository;

    @Override
    public Point getById(Long id) {
        log.info("In PointsServiceImpl getById {}", id);
        return pointRepository.getOne(id);
    }

    @Override
    public void save(Point point, Owner owner) {
        log.info("In PointsServiceImpl save {}", point);
        point.setOwner(owner);
        pointRepository.save(point);
    }

    @Override
    public List<Point> getAll(Owner owner) {
        log.info("In PointsServiceImpl getAll {}", owner.getId());
        return pointRepository.findAllPointsByOwnerName(owner.getId());
    }
}

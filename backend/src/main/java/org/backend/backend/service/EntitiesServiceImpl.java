package org.backend.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.backend.backend.exception.PointValidationException;
import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;
import org.backend.backend.repository.PointRepository;
import org.backend.backend.util.PointResultChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.backend.backend.repository.OwnerRepository;

import java.util.List;

@Slf4j
@Service
public class EntitiesServiceImpl implements EntitiesService {
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    PointResultChecker pointResultChecker;

    // Points Service Realization

    @Override
    public void savePoint(Point point, String username) throws PointValidationException {
        log.info("In PointsServiceImpl save {}", point);
        if (existsOwnerByName(username)){
            Owner owner = ownerRepository.getOwnerByName(username);
            point.setOwner(owner);
            point.setResult(pointResultChecker.checkResult(point));
        }
        pointRepository.save(point);
    }

    @Override
    public List<Point> getAllPoints(String username) {
        log.info("In PointsServiceImpl getAll {}", username);
        return existsOwnerByName(username) ? pointRepository.findAllPointsByOwnerName(username) : null;
    }

    //Owner Service Realization

    @Override
    public boolean addNewOwner(Owner owner) {
        if (existsOwnerByName(owner.getUsername())){
           log.error("Owner with this username exists!");
           return false;
        }
        log.info("In OwnerServiceImpl addNewOwner {} has created new owner", owner);
        ownerRepository.save(owner);
        return true;
    }

    @Override
    public boolean existsOwnerByName(String name) {
        return ownerRepository.getOwnerByName(name) != null;
    }

    @Override
    public boolean existsOwnerByNameAndPassword(String name, String password) {
        return ownerRepository.getOwnerByNameAndPassword(name, password) != null;
    }
}

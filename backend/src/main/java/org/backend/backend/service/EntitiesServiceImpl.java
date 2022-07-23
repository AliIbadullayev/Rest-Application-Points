package org.backend.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;
import org.backend.backend.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.backend.backend.repository.OwnerRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EntitiesServiceImpl implements OwnerService, PointsService{
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PointRepository pointRepository;

    // Points Service Realization
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

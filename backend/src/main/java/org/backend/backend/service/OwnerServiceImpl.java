package org.backend.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.backend.backend.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.backend.backend.repository.OwnerRepository;

@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService{
    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public boolean addNewOwner(Owner owner) {
            log.info("In OwnerServiceImpl addNewOwner {} has created new owner", owner);
            ownerRepository.save(owner);
            System.out.println(ownerRepository.getOwnerByName(owner.getName()).get(0).toString());
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

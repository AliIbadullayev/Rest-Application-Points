package org.backend.backend.service;

import org.backend.backend.model.Owner;

public interface OwnerService {
    boolean addNewOwner(Owner owner);
    boolean existsOwnerByName(String name);
    boolean existsOwnerByNameAndPassword(String name, String Password);
}

package org.backend.backend.repository;

import org.backend.backend.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query(value = "SELECT u FROM Owner u WHERE u.username = :name")
    Owner getOwnerByName(@Param(value = "name") String name);

    @Query(value = "Select u From Owner u where u.username = :name and u.password = :password")
    Owner getOwnerByNameAndPassword(@Param(value = "name") String name,
                                    @Param(value = "password") String password
    );
}

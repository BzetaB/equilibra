package com.bzetab.equilibra.repositories;

import com.bzetab.equilibra.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUniqueCode(String uniqueCode);
}
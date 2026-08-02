package com.bzetab.equilibra.repository;

import com.bzetab.equilibra.repository.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUniqueCode(String uniqueCode);
}
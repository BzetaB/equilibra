package com.bzetab.equilibra.services.impl;

import com.bzetab.equilibra.models.Owner;
import com.bzetab.equilibra.repositories.OwnerRepository;
import com.bzetab.equilibra.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner getOwnerByUniqueCode(String uniqueCode) {

        if(uniqueCode == null || uniqueCode.isBlank()){
            throw new RuntimeException("The unique code must not be blank");
        }

        return ownerRepository
                .findByUniqueCode(uniqueCode)
                .orElseThrow(()-> new RuntimeException("Owner not found with unique code: " + uniqueCode));
    }

    @Override
    public Owner createOwner(String uniqueCode) {

        if(uniqueCode == null || uniqueCode.isBlank()){
            throw new RuntimeException("The unique code must not be blank");
        }

        if(ownerRepository.findByUniqueCode(uniqueCode).isPresent()){
            throw new RuntimeException("Owner already exist with the unique code: " + uniqueCode);
        }

        return ownerRepository
                .save(Owner.builder()
                        .uniqueCode(uniqueCode)
                        .isActive(true)
                        .build());
    }

    @Override
    public Owner updatedStatusOwner(String uniqueCode) {

        Owner existingOwner = this.getOwnerByUniqueCode(uniqueCode);

        existingOwner.setIsActive(!existingOwner.getIsActive());

        return ownerRepository.save(existingOwner);
    }
}

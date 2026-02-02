package com.bzetab.equilibra.owner.service;

import com.bzetab.equilibra.owner.entity.Owner;

public interface OwnerService {
    Owner getOwnerByUniqueCode(String uniqueCode);
    Owner createOwner(String uniqueCode);
    Owner updatedStatusOwner(String uniqueCode);
}

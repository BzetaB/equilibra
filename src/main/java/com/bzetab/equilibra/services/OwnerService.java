package com.bzetab.equilibra.services;

import com.bzetab.equilibra.models.Owner;

public interface OwnerService {
    Owner getOwnerByUniqueCode(String uniqueCode);
    Owner createOwner(String uniqueCode);
    Owner updatedStatusOwner(String uniqueCode);
}

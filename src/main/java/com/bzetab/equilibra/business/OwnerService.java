package com.bzetab.equilibra.business;

import com.bzetab.equilibra.repository.model.Owner;

public interface OwnerService {
    Owner getOwnerByUniqueCode(String uniqueCode);
    Owner createOwner(String uniqueCode);
    Owner updatedStatusOwner(String uniqueCode);
}

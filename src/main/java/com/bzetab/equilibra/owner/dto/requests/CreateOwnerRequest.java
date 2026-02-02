package com.bzetab.equilibra.owner.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOwnerRequest {
    private String uniqueCode;
}

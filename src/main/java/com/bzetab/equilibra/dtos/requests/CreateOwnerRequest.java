package com.bzetab.equilibra.dtos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOwnerRequest {
    private String uniqueCode;
}

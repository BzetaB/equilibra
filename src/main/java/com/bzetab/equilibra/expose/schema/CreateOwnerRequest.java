package com.bzetab.equilibra.expose.schema;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOwnerRequest {
    private String uniqueCode;
}

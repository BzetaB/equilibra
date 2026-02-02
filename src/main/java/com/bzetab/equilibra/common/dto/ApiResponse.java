package com.bzetab.equilibra.common.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "timestamp",
        "status",
        "message",
        "data"
})
@Data
@Builder
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private T data;
}
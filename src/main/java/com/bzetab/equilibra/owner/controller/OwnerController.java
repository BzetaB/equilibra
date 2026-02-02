package com.bzetab.equilibra.owner.controller;

import com.bzetab.equilibra.common.dto.ApiResponse;
import com.bzetab.equilibra.owner.dto.requests.CreateOwnerRequest;
import com.bzetab.equilibra.owner.entity.Owner;
import com.bzetab.equilibra.owner.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Owner>> getOwnerByUniqueCode(@RequestParam("uniqueCode") String uniqueCode){
        return ResponseEntity.ok(
                ApiResponse.<Owner>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Owner found successfully")
                        .data(ownerService.getOwnerByUniqueCode(uniqueCode))
                        .build()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Owner>> createOwner(@RequestBody CreateOwnerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Owner>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED.value())
                        .message("Owner created successfully")
                        .data(ownerService.createOwner(request.getUniqueCode()))
                        .build()
        );
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Owner>> updateStatusOwner(@RequestParam("uniqueCode") String uniqueCode){
        return ResponseEntity.ok(
                ApiResponse.<Owner>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Owner status updated successfully")
                        .data(ownerService.updatedStatusOwner(uniqueCode))
                        .build()
        );
    }
}

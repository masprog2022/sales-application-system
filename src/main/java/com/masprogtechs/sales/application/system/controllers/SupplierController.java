package com.masprogtechs.sales.application.system.controllers;

import com.masprogtechs.sales.application.system.domain.entities.dto.supplier.SupplierDTO;
import com.masprogtechs.sales.application.system.services.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    @Operation(summary = "Salvar um fornecdor", description = "Salvar um fornecedor",
            tags = {"Fornecedor"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SupplierDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<SupplierDTO> registerCategory(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO savedSupplierDTO = supplierService.registerSupplier(supplierDTO);
        return ResponseEntity.ok(savedSupplierDTO);
    }
}

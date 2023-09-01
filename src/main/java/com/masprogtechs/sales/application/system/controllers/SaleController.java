package com.masprogtechs.sales.application.system.controllers;

import com.masprogtechs.sales.application.system.domain.entities.dto.error.ErrorDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.sale.SaleRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.sale.SaleResponseDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserDTO;
import com.masprogtechs.sales.application.system.exception.InsufficientStockException;
import com.masprogtechs.sales.application.system.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sales")
@Tag(name = "Venda", description = "Endpoints para gerenciar vendas")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    @Operation(summary = "Salvar uma venda", description = "Salvar uma venda",
            tags = {"Venda"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SaleResponseDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> createSale(@RequestBody SaleRequestDTO saleRequestDTO) {
        try {
            SaleResponseDTO response = saleService.createSale(saleRequestDTO);
            return ResponseEntity.ok(response);
        } catch (InsufficientStockException e) {
            ErrorDTO errorDTO = new ErrorDTO("Insufficient stock for some items.");
            return ResponseEntity.badRequest().body(errorDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

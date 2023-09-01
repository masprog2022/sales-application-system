package com.masprogtechs.sales.application.system.controllers;


import com.masprogtechs.sales.application.system.domain.entities.dto.stock.StockDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.stock.user.UserDTO;
import com.masprogtechs.sales.application.system.services.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stocks")
@Tag(name = "Estoque", description = "Endpoints para gerenciar estoques")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    @Operation(summary = "Registar produto em estoque", description = "Registar produto em estoque",
            tags = {"Estoque"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StockDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<StockDTO> registerStock(@RequestBody StockDTO stockDTO) {
        StockDTO savedStockDTO = stockService.registerStock(stockDTO);
        return ResponseEntity.ok(savedStockDTO);
    }
}

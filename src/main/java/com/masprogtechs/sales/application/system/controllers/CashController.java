package com.masprogtechs.sales.application.system.controllers;

import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashReduceDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashRequestDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.cash.CashResponseDTO;
import com.masprogtechs.sales.application.system.exception.UnauthorizedException;
import com.masprogtechs.sales.application.system.exception.UserNotFoundException;
import com.masprogtechs.sales.application.system.services.CashService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cash")
@Tag(name = "Caixa", description = "Endpoints para gerenciar abertura e fecho e caixa")
public class CashController {

    @Autowired
    private CashService cashService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/open")
    @Operation(summary = "Abertura de caixa", description = "Abertura de caixa",
            tags = {"Caixa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CashReduceDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> openCash(@RequestBody CashRequestDTO cashDTO) {
        try {
            CashResponseDTO cash = cashService.openCash(cashDTO);
            return ResponseEntity.ok(cash);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: " + e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        }
    }
}

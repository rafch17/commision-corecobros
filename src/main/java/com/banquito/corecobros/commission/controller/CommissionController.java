package com.banquito.corecobros.commission.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.service.CommissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/commission-microservice/api/v1/commissions")
@Tag(name = "Commission", description = "Endpoints for managing commission")
public class CommissionController {

    private final CommissionService commissionService;

    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping("/{uniqueId}")
    @Operation(summary = "Get commission by uniqueId", description = "Retrieve a commission by its uniqueId")
    public ResponseEntity<CommissionDTO> getByUniqueId(@PathVariable String uniqueId) {
        try {
            Optional<CommissionDTO> commission = commissionService.obtainCommissionByUniqueId(uniqueId);
            return commission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/item-commissions/{uniqueId}")
    @Operation(summary = "Get ItemCommission by uniqueId", description = "Retrieve a ItemCommission by its uniqueId")
    public ResponseEntity<List<Commission>> getByItemCommissionUniqueId(@PathVariable String uniqueId) {
        List<Commission> commissions = commissionService.obtainCommissionsByItemCommissionUniqueId(uniqueId);
        if (commissions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(commissions);
        }
    }

    @GetMapping("/item-commissionsById/{id}")
    @Operation(summary = "Get ItemCommission by Id", description = "Retrieve a ItemCommission by its Id")
    public ResponseEntity<List<Commission>> getByItemCommissionId(@PathVariable String id) {
        List<Commission> commissions = commissionService.obtainCommissionsByItemCommissionId(id);
        if (commissions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(commissions);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Create a commission", description = "Create a new commission")
    public ResponseEntity<CommissionDTO> create(@RequestBody CommissionDTO commissionDTO) {
        try {
            CommissionDTO savedCommission = commissionService.create(commissionDTO);
            return ResponseEntity.ok(savedCommission);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all commissions", description = "Retrieve a list of all commissions")
    public ResponseEntity<List<Commission>> getAllCommissions() {
        List<Commission> commissions = commissionService.getAllCommissions();
        if (commissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(commissions);
        }
    }

}

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

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/v1/commissions")
public class CommisionController {

    private final CommissionService commissionService;

    public CommisionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping("/{uniqueId}")
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
    public ResponseEntity<List<Commission>> getByItemCommissionUniqueId(@PathVariable String uniqueId) {
        List<Commission> commissions = commissionService.obtainCommissionsByItemCommissionUniqueId(uniqueId);
        if (commissions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(commissions);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CommissionDTO> create(@RequestBody CommissionDTO commissionDTO) {
        try {
            CommissionDTO savedCommission = commissionService.create(commissionDTO);
            return ResponseEntity.ok(savedCommission);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}

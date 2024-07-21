package com.banquito.corecobros.commission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.service.CommissionService;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/commissions")
public class CommisionController {

    private final CommissionService commissionService;

    public CommisionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping("/{uniqueId}")
    public ResponseEntity<CommissionDTO> getCommissionByUniqueId(@PathVariable String uniqueId) {
        try {
            Optional<CommissionDTO> commission = commissionService.obtainCommissionByUniqueId(uniqueId);
            return commission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}

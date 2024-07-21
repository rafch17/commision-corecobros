package com.banquito.corecobros.commission.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.service.ItemCommissionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@RequestMapping("/receivablecommissions")
public class ItemCommissionController {

    private final ItemCommissionService itemCommissionService;

    public ItemCommissionController(ItemCommissionService itemCommissionService) {
        this.itemCommissionService = itemCommissionService;
    }

    @GetMapping("/{uniqueId}")
    public ResponseEntity<List<ItemCommissionDTO>> getItemCommissionByCommissionUniqueId(
            @PathVariable String uniqueId) {
        try {
            List<ItemCommissionDTO> itemCommission = itemCommissionService
                    .obtainItemCommissionByCommissionUniqueId(uniqueId);
            return ResponseEntity.ok(itemCommission);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}

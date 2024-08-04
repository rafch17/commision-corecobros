package com.banquito.corecobros.commission.controller;

import java.util.List;

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
import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.service.ItemCommissionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@RequestMapping("/api/v1/item-commissions")
public class ItemCommissionController {

    private final ItemCommissionService itemCommissionService;

    public ItemCommissionController(ItemCommissionService itemCommissionService) {
        this.itemCommissionService = itemCommissionService;
    }

    @GetMapping("/{uniqueId}")
    public ResponseEntity<List<ItemCommissionDTO>> getItemCommissionByCommissionUniqueId(
            @PathVariable String uniqueId) {
        try {
            List<ItemCommissionDTO> itemCommission = itemCommissionService.obtainItemCommissionByUniqueId(uniqueId);
            return ResponseEntity.ok(itemCommission);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ItemCommissionDTO> create(@RequestBody ItemCommissionDTO itemCommissionDTO) {
        try {
            ItemCommissionDTO savedItemCommission = itemCommissionService.create(itemCommissionDTO);
            return ResponseEntity.ok(savedItemCommission);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/item-collection/{itemCollectionUniqueId}")
    public ResponseEntity<ItemCommissionDTO> getByItemCollectionUniqueId(@PathVariable String itemCollectionUniqueId) {
        try {
            ItemCommissionDTO itemCommission = itemCommissionService.getByItemCollectionUniqueId(itemCollectionUniqueId);
            return ResponseEntity.ok(itemCommission);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    

    @GetMapping("/get-total-by-order/{orderUniqueId}/{companyUniqueId}")
    public ResponseEntity<CommissionDTO> getByItem(@PathVariable String orderUniqueId, @PathVariable String companyUniqueId) {
        return ResponseEntity.ok(itemCommissionService.sumTotalCommissionValueByOrderUniqueId(orderUniqueId, companyUniqueId));
    }
}

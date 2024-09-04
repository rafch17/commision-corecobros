package com.banquito.corecobros.commission.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.service.CommissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CommissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommissionService commissionService;

    @Test
    void testGetAllCommissions() throws Exception {
        when(commissionService.getAllCommissions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/commission-microservice/api/v1/commissions"))
                .andExpect(status().isNoContent());

        Commission commission = new Commission();
        when(commissionService.getAllCommissions()).thenReturn(List.of(commission));

        mockMvc.perform(get("/commission-microservice/api/v1/commissions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreate() throws Exception {
        CommissionDTO commissionDTO = CommissionDTO.builder()
                .id(1)
                .name("Comision por recaudo")
                .uniqueId("JVI0024966")
                .chargeDistribution("TOD")
                .value(new BigDecimal("0.40"))
                .totalValue(new BigDecimal("0.00"))
                .companyValue(new BigDecimal("0.40"))
                .companyUniqueId("2267578945")
                .debtorValue(new BigDecimal("0.40"))
                .creditorAccount("OJU0037961")
                .build();

        when(commissionService.create(any(CommissionDTO.class))).thenReturn(commissionDTO);

        mockMvc.perform(post("/commission-microservice/api/v1/commissions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"id\":1,"
                        + "\"name\":\"Comision por recaudo\","
                        + "\"uniqueId\":\"JVI0024966\","
                        + "\"chargeDistribution\":\"TOD\","
                        + "\"value\":0.40,"
                        + "\"totalValue\":0.00,"
                        + "\"companyValue\":0.40,"
                        + "\"companyUniqueId\":\"2267578945\","
                        + "\"debtorValue\":0.40,"
                        + "\"creditorAccount\":\"OJU0037961\""
                        + "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetByUniqueId() throws Exception {
        String uniqueId = "uniqueId";

        CommissionDTO commissionDTO = CommissionDTO.builder()
                .id(1)
                .name("Test Commission")
                .uniqueId(uniqueId)
                .chargeDistribution("TOD")
                .value(new BigDecimal("0.40"))
                .totalValue(new BigDecimal("0.00"))
                .companyValue(new BigDecimal("0.40"))
                .companyUniqueId("2267578945")
                .debtorValue(new BigDecimal("0.40"))
                .creditorAccount("OJU0037961")
                .build();

        when(commissionService.obtainCommissionByUniqueId(uniqueId)).thenReturn(Optional.of(commissionDTO));

        mockMvc.perform(get("/commission-microservice/api/v1/commissions/" + uniqueId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uniqueId").value(uniqueId));

        when(commissionService.obtainCommissionByUniqueId(uniqueId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/commission-microservice/api/v1/commissions/" + uniqueId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetByItemCommissionUniqueId() throws Exception {
        String uniqueId = "uniqueId";
        when(commissionService.obtainCommissionsByItemCommissionUniqueId(uniqueId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/commission-microservice/api/v1/commissions/item-commissions/" + uniqueId))
                .andExpect(status().isNotFound());

        Commission commission = new Commission();
        when(commissionService.obtainCommissionsByItemCommissionUniqueId(uniqueId)).thenReturn(List.of(commission));

        mockMvc.perform(get("/commission-microservice/api/v1/commissions/item-commissions/" + uniqueId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetByItemCommissionId() throws Exception {
        String id = "id";
        when(commissionService.obtainCommissionsByItemCommissionId(id)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/commission-microservice/api/v1/commissions/item-commissionsById/" + id))
                .andExpect(status().isNotFound());

        Commission commission = new Commission();
        when(commissionService.obtainCommissionsByItemCommissionId(id)).thenReturn(List.of(commission));

        mockMvc.perform(get("/commission-microservice/api/v1/commissions/item-commissionsById/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}

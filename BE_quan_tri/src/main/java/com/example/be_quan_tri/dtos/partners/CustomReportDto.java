package com.example.be_quan_tri.dtos.partners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomReportDto {

    private Long idPartner;
    private String partnerName;
    private String partnerCode;
    private Long totalRequests;
    private Long totalSuccessfulRequests;
    private Long totalCost;

}

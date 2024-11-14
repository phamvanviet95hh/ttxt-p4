package com.example.be_quan_tri.dtos.partners;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomReportDetailDtos {

    private Long idPartner;
    private String serviceName;
    private Long totalRequest;
    private Long totalSuccessfulRequests;
    private Long totalCost;
}

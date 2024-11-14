package com.example.be_quan_tri.dtos.reports;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class fomatReportServiceDtosNew {
    private Long id;
    private String sateServiceName;
    private String serviceName;
    private Long totalTransaction;
    private Long totalTransactionSuccess;
    private Long totalMoney;
    private int rowspan;

}

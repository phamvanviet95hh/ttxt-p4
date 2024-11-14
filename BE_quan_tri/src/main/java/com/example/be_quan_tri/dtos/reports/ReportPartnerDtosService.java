package com.example.be_quan_tri.dtos.reports;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportPartnerDtosService {
    private Long id;
    private String partnerCode;
    private Long totalTransaction;
    private Long totalTransactionSuccess;
    private Long totalMoney;
}

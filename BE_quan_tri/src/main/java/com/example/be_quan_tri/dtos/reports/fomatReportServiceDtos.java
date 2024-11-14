package com.example.be_quan_tri.dtos.reports;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class fomatReportServiceDtos {
    private Long id;
    private String sateServiceName;
    private String serviceName;
    private Long totalTransaction;
    private Long totalTransactionSuccess;
    private Long totalMoney;

    public fomatReportServiceDtosNew getVo() {

        fomatReportServiceDtosNew fomatReportServiceDtosNew = new fomatReportServiceDtosNew();
        BeanUtils.copyProperties(this, fomatReportServiceDtosNew);
        fomatReportServiceDtosNew.setRowspan(0);
        return fomatReportServiceDtosNew;

    }
}

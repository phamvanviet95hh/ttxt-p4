package com.example.be_quan_tri.dtos.partners;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomReportDetailDtosPa {

    private Long idPartner;
    private String partnerCode;
    private String categoryName;
    private String serviceName;
    private Long totalRequest;
    private Long totalSuccessfulRequests;
    private Long totalCost;

    public CustomReportDetailDtosPaNew getVo() {
        CustomReportDetailDtosPaNew vo = new CustomReportDetailDtosPaNew();
        BeanUtils.copyProperties(this, vo);
        vo.setRowspan(0);
        return vo;

    }
}

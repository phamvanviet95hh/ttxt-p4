package com.example.be_quan_tri.dtos.dashboard;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomThongKeAdmin {
    private Integer year;
    private String partnerCode;
    private Long totalMoney;
}

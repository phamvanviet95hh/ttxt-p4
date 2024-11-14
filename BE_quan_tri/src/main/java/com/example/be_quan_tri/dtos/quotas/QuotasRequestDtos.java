package com.example.be_quan_tri.dtos.quotas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotasRequestDtos {
    private Long id;
    private String quotaCode;
    private Long quotas;
}

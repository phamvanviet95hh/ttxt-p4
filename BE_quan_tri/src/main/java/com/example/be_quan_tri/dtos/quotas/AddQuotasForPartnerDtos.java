package com.example.be_quan_tri.dtos.quotas;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddQuotasForPartnerDtos {

    private Long idQuota;
    private Long idPartner;
    private Long idCategory;

}

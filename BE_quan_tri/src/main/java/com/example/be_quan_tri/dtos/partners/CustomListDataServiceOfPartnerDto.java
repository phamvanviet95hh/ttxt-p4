package com.example.be_quan_tri.dtos.partners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomListDataServiceOfPartnerDto {

    private Long partnerId;
    private Long categoryId;
    private String partnerCode;
    private String categoryName;
    private String serviceName;
    private LocalDateTime createdAt;

}

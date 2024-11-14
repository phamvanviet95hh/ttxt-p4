package com.example.be_quan_tri.dtos.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomTopPartner {

    private Long partnerId;
    private String partnerName;
    private String partnerCode;
    private String partnerUser;
    private Long countCategory;

}

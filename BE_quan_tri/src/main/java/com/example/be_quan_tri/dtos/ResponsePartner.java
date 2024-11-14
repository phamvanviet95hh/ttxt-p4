package com.example.be_quan_tri.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResponsePartner {

    private Long id;
    private String partnerName;

    private String partnerCode;

    private String partnerApiKey;
    private String partnerDetail;
    private String partnerAddress;
    private String partnerLogo;

}


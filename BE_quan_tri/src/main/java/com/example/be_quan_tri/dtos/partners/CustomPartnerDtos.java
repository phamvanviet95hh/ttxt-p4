package com.example.be_quan_tri.dtos.partners;



import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPartnerDtos {

    private Long id;
    private String partnerName;

    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime goLiveDate;
    private LocalDateTime dateEndLive;

    private String role;

    private String status;
    private String partnerCode;

    private String partnerDetail;
    private String partnerAddress;
    private String partnerTax;
    private String partnerEmail;
    private String partnerUser;
    private String partnerPhone;

    private Long quotas;

}

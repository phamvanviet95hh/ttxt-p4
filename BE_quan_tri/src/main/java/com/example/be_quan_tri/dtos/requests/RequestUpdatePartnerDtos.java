package com.example.be_quan_tri.dtos.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RequestUpdatePartnerDtos {
    private Long id;
    private String partnerName;
    private String userName;
    private String password;
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
}

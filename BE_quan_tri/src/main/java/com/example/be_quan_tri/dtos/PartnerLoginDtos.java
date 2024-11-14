package com.example.be_quan_tri.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PartnerLoginDtos {

    private Long id;
    private String partnerName;

    private String userName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String role;

    private String status;
    private String partnerCode;

    private String partnerApiKey;
    private String partnerDetail;
    private String partnerAddress;

    private String partnerTax;

    private String partnerPhone;
    private String partnerUser;
    private String partnerEmail;
    private String partnerLogo;

}

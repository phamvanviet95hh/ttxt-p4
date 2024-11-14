package com.example.be_quan_tri.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {
    private String partnerName;
    private String userName;
    private String password;
    private String role;
    private String status;
    private String partnerCode;
    private String partnerDetail;
    private String partnerAddress;
    private String partnerTax;
    private String partnerEmail;
    private String partnerUser;
    private String partnerPhone;
    private LocalDateTime dateGoLive;

}

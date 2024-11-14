package com.example.be_quan_tri.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserLoginResponse {
    private Boolean success;
    private String message;
    private PartnerLoginDtos data;
    private String token;
}

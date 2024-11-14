package com.example.be_quan_tri.dtos.partners;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCheckPassword {

    private Long partnerId;
    private String password;
    private String newPassword;
}

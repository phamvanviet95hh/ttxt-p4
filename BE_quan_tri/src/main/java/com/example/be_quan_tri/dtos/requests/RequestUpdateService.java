package com.example.be_quan_tri.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequestUpdateService {

    private Long id;
    private String serviceName;
    private String serviceDescription;
    private String serviceCode;
    private Long servicePrice;
    private LocalDateTime updateAt;

}

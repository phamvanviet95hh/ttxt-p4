package com.example.be_quan_tri.dtos;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseService {

    private Long id;
    private String serviceName;
    private String servicePath;

}

package com.example.be_quan_tri.dtos.services;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryServicesDtos {

    private Long id;
    private String categoryServiceName;
}

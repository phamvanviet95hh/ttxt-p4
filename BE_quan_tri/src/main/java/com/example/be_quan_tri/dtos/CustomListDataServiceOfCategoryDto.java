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

public class CustomListDataServiceOfCategoryDto {

    private Long id;
    private Long categoryId;
    private Long serviceId;
    private String serviceName;
    private LocalDateTime createdAt;

}

package com.example.be_quan_tri.dtos.partners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomCategoryDtos {

    private Long id;
    private Long partnerId;
    private Long categoryServiceId;
    private String categoryServiceName;

}

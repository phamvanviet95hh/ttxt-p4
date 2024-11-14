package com.example.be_quan_tri.dtos.partners;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomReportDetailDtosPa1 {
    private Long categoryId;
    private String categoryName;
    private LocalDateTime dateGolive;
}

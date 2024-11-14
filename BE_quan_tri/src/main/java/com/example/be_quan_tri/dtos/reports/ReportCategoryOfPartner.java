package com.example.be_quan_tri.dtos.reports;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCategoryOfPartner {

    private Long idCate;
    private String categoryName;
    private Long totalTransaction;
    private Long totalTransactionSuccess;

}

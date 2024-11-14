package com.example.be_quan_tri.dtos.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GloableReportResponse {

    private Boolean success;
    private String message;
    private String link;

}

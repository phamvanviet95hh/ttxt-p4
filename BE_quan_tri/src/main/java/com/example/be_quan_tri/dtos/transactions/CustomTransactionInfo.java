package com.example.be_quan_tri.dtos.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomTransactionInfo {

    private String path;
    private String orgCode;
    private String idQ;
    private String statusCode;
    private String requestId;
    private String serviceCodeRequest;
    private Timestamp timeRequest;

}
